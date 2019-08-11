package org.com.raian.nycschoolschase.ui.schools.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.com.raian.nycschoolschase.constants.GlobalConstants.Companion.baseURL
import org.com.raian.nycschoolschase.database.SchoolDataBase
import org.com.raian.nycschoolschase.database.model.UIDataClass
import org.com.raian.nycschoolschase.dependency.injection.components.DaggerComponentInjector
import org.com.raian.nycschoolschase.dependency.injection.modules.ContextModule
import org.com.raian.nycschoolschase.dependency.injection.modules.NetworkModule
import org.com.raian.nycschoolschase.rest.RestApi
import org.com.raian.nycschoolschase.rest.model.schools.SchoolsResultRestApi
import org.com.raian.nycschoolschase.rest.model.schools.ScoresResultRestApi
import org.com.raian.nycschoolschase.ui.viewmodels.BaseViewModel
import org.com.raian.nycschoolschase.util.safeLet
import retrofit2.Retrofit
import java.util.logging.Logger
import javax.inject.Inject

class SchoolsViewModel(private val dataBase: SchoolDataBase, context: Context) : BaseViewModel() {
    @Inject
    lateinit var retrofit: Retrofit

    private val listOfSchoolsForUI by lazy {
        MutableLiveData<List<UIDataClass>>()
    }

    private var injector = DaggerComponentInjector
        .builder()
        .networkModule(NetworkModule(baseURL))
        .contextModule(ContextModule(context))
        .build()

    init {
        TAG = SchoolsViewModel::class.java.simpleName
        logger = Logger.getLogger(TAG)
        inject()
        checkLocalData()
    }

    private fun inject() {
        injector.inject(this)
        restApi = retrofit.create(RestApi::class.java)
    }

    private fun checkLocalData() {
        GlobalScope.launch(Dispatchers.IO) {
            val lstRes: List<UIDataClass>? = dataBase.schoolDao().getSchoolsJoin()
            if (lstRes.isNullOrEmpty()) {
                prepareRemoteData()
            } else {
                getStoredDataForUI()
            }
        }
    }

    private fun prepareRemoteData() {
        with(CoroutineScope(Dispatchers.IO + supervisorJob)){
            val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                logger.severe("Something went wrong:: ${throwable.cause}::${throwable.message}")
            }

            val deferredSchools = async {
                restApi.getSchools().execute().body()
            }
            val deferredScores = async {
                restApi.getScores().execute().body()
            }

            launch (exceptionHandler){
                safeLet(deferredSchools.await(), deferredScores.await()) { schools, scores ->
                    lambdaStoreInDb(schools, scores)
                }
            }
        }
    }

    val lambdaStoreInDb: (lstOfSchools: List<SchoolsResultRestApi>, listOfScores: List<ScoresResultRestApi>) -> Unit =
        { schools, scores ->
            GlobalScope.launch(Dispatchers.IO) {
                for (i in schools) {
                    with(i) {
                        val innerSchool = org.com.raian.nycschoolschase.database.model.SchoolDataClass(
                            attendanceRate = attendanceRate,
                            bbl = bbl,
                            bin = bin,
                            boro = boro,
                            borough = borough,
                            buildingCode = buildingCode,
                            bus = bus,
                            censusTract = censusTract,
                            city = city,
                            code1 = code1,
                            communityBoard = communityBoard,
                            councilDistrict = councilDistrict,
                            dbn = dbn,
                            directions1 = directions1,
                            ellPrograms = ellPrograms,
                            extracurricularActivities = extracurricularActivities,
                            faxNumber = faxNumber,
                            finalgrades = finalgrades,
                            interest1 = interest1,
                            latitude = latitude,
                            location = location,
                            longitude = longitude,
                            method1 = method1,
                            neighborhood = neighborhood,
                            nta = nta,
                            offerRate1 = offerRate1,
                            overviewParagraph = overviewParagraph,
                            pctStuEnoughVariety = pctStuEnoughVariety,
                            pctStuSafe = pctStuSafe,
                            phoneNumber = phoneNumber,
                            primaryAddressLine1 = primaryAddressLine1,
                            program1 = program1,
                            school10thSeats = school10thSeats,
                            schoolAccessibilityDescription = schoolAccessibilityDescription,
                            schoolEmail = schoolEmail,
                            schoolName = schoolName,
                            schoolSports = schoolSports,
                            subway = subway,
                            website = website,
                            zip = zip,
                            academicopportunities3 = academicopportunities3,
                            addtlInfo1 = addtlInfo1,
                            eligibility1 = eligibility1,
                            languageClasses = languageClasses,
                            transfer = transfer
                        )
                        dataBase.schoolDao().insert(innerSchool)
                    }
                }

                for (i in scores) {
                    with(i) {
                        val innerScores = org.com.raian.nycschoolschase.database.model.ScoresDataClass(
                            dbn = dbn,
                            readingScore = readingScore,
                            mathScore = mathScore,
                            writingScore = writingScore,
                            schoolName = schoolName,
                            numOfSatTestTakers = numOfSatTestTakers
                        )
                        dataBase.scoresDao().insert(innerScores)
                    }
                }
                listOfSchoolsForUI.postValue(dataBase.schoolDao().getSchoolsJoin())
            }
        }

    private fun getStoredDataForUI() = GlobalScope.launch(Dispatchers.IO) {
        listOfSchoolsForUI.postValue(dataBase.schoolDao().getSchoolsJoin())
    }

    fun getListOfSchoolsForUI(): LiveData<List<UIDataClass>> {
        return listOfSchoolsForUI
    }
}