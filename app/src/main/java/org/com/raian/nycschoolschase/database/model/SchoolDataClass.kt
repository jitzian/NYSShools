package org.com.raian.nycschoolschase.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "school")
data class SchoolDataClass(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "attendanceRate") var attendanceRate: String? = null,
    @ColumnInfo(name = "bbl") var bbl: String? = null,
    @ColumnInfo(name = "bin") var bin: String? = null,
    @ColumnInfo(name = "boro") var boro: String? = null,
    @ColumnInfo(name = "borough") var borough: String? = null,
    @ColumnInfo(name = "buildingCode") var buildingCode: String? = null,
    @ColumnInfo(name = "bus") var bus: String? = null,
    @ColumnInfo(name = "censusTract") var censusTract: String? = null,
    @ColumnInfo(name = "city") var city: String? = null,
    @ColumnInfo(name = "code1") var code1: String? = null,
    @ColumnInfo(name = "communityBoard") var communityBoard: String? = null,
    @ColumnInfo(name = "councilDistrict") var councilDistrict: String? = null,
    @ColumnInfo(name = "dbn") var dbn: String? = null,
    @ColumnInfo(name = "directions1") var directions1: String? = null,
    @ColumnInfo(name = "ellPrograms") var ellPrograms: String? = null,
    @ColumnInfo(name = "extracurricularActivities") var extracurricularActivities: String? = null,
    @ColumnInfo(name = "faxNumber") var faxNumber: String? = null,
    @ColumnInfo(name = "finalgrades") var finalgrades: String? = null,
    @ColumnInfo(name = "interest1") var interest1: String? = null,
    @ColumnInfo(name = "latitude") var latitude: String? = null,
    @ColumnInfo(name = "location") var location: String? = null,
    @ColumnInfo(name = "longitude") var longitude: String? = null,
    @ColumnInfo(name = "method1") var method1: String? = null,
    @ColumnInfo(name = "neighborhood") var neighborhood: String? = null,
    @ColumnInfo(name = "nta") var nta: String? = null,
    @ColumnInfo(name = "offerRate1") var offerRate1: String? = null,
    @ColumnInfo(name = "overviewParagraph") var overviewParagraph: String? = null,
    @ColumnInfo(name = "pctStuEnoughVariety") var pctStuEnoughVariety: String? = null,
    @ColumnInfo(name = "pctStuSafe") var pctStuSafe: String? = null,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String? = null,
    @ColumnInfo(name = "primaryAddressLine1") var primaryAddressLine1: String? = null,
    @ColumnInfo(name = "program1") var program1: String? = null,
    @ColumnInfo(name = "school10thSeats") var school10thSeats: String? = null,
    @ColumnInfo(name = "schoolAccessibilityDescription") var schoolAccessibilityDescription: String? = null,
    @ColumnInfo(name = "schoolEmail") var schoolEmail: String? = null,
    @ColumnInfo(name = "schoolName") var schoolName: String? = null,
    @ColumnInfo(name = "schoolSports") var schoolSports: String? = null,
    @ColumnInfo(name = "subway") var subway: String? = null,
    @ColumnInfo(name = "website") var website: String? = null,
    @ColumnInfo(name = "zip") var zip: String? = null,
    @ColumnInfo(name = "academicopportunities3") var academicopportunities3: String? = null,
    @ColumnInfo(name = "addtlInfo1") var addtlInfo1: String? = null,
    @ColumnInfo(name = "eligibility1") var eligibility1: String? = null,
    @ColumnInfo(name = "languageClasses") var languageClasses: String? = null,
    @ColumnInfo(name = "transfer") var transfer: String? = null
) {
    var isExpanded: Boolean = false
}