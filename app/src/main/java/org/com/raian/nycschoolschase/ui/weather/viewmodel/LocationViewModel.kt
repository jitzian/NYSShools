package org.com.raian.nycschoolschase.ui.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.com.raian.nycschoolschase.constants.GlobalConstants.Companion.weatherBaseUrl
import org.com.raian.nycschoolschase.dependency.injection.components.DaggerComponentInjector
import org.com.raian.nycschoolschase.dependency.injection.modules.NetworkModule
import org.com.raian.nycschoolschase.rest.RestApi
import org.com.raian.nycschoolschase.rest.model.weather.WeatherResultApi
import org.com.raian.nycschoolschase.ui.viewmodels.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.logging.Logger
import javax.inject.Inject

class LocationViewModel : BaseViewModel() {
    @Inject
    lateinit var retrofit: Retrofit

    private val region by lazy {
        MutableLiveData<String>()
    }

    private val temperatureInCelsius by lazy {
        MutableLiveData<String>()
    }

    private val parameterForDisplayingWeatherIcon by lazy {
        MutableLiveData<String>()
    }

    private var injector = DaggerComponentInjector
        .builder()
        .networkModule(NetworkModule(weatherBaseUrl))
        .build()

    init {
        TAG = LocationViewModel::class.java.simpleName
        logger = Logger.getLogger(TAG)
        inject()
    }

    private fun inject() {
        injector.inject(this)
        restApi = retrofit.create(RestApi::class.java)
    }

    fun getRemoteData(location: String) = GlobalScope.launch(Dispatchers.IO) {
        val deferredWeather = async {
            restApi.getWeatherFromCurrentLocation(location).enqueue(object : Callback<WeatherResultApi> {
                override fun onFailure(call: Call<WeatherResultApi>, t: Throwable) {
                    logger.severe("$TAG::getRemoteData::onFailure::${t.message}::${t.cause}")
                    region.postValue("Data Not available")
                    temperatureInCelsius.postValue("Data Not available")

                }

                override fun onResponse(call: Call<WeatherResultApi>, response: Response<WeatherResultApi>) {
                    logger.severe("$TAG::getRemoteData::onResponse::${response.body()?.location?.region}")
                    region.postValue(response.body()?.location?.region)
                    temperatureInCelsius.postValue(response.body()?.current?.tempC.toString())
                }
            })
        }
        deferredWeather.await()
    }

    fun getRegion(): LiveData<String>{
        return region
    }

    fun getTemperatureInCelsius(): LiveData<String>{
        return temperatureInCelsius
    }

    fun getParameterForDisplayingWeatherIcon(): LiveData<String>{
        return parameterForDisplayingWeatherIcon
    }

}