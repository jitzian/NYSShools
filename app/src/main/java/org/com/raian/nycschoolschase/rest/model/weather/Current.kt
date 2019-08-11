package org.com.raian.nycschoolschase.rest.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Current {

    @SerializedName("last_updated_epoch")
    @Expose
    var lastUpdatedEpoch: Long? = null
    @SerializedName("last_updated")
    @Expose
    var lastUpdated: String? = null
    @SerializedName("temp_c")
    @Expose
    var tempC: Double? = null
    @SerializedName("temp_f")
    @Expose
    var tempF: Double? = null
    @SerializedName("is_day")
    @Expose
    var isDay: Long? = null
    @SerializedName("condition")
    @Expose
    var condition: Condition? = null
    @SerializedName("wind_mph")
    @Expose
    var windMph: Double? = null
    @SerializedName("wind_kph")
    @Expose
    var windKph: Double? = null
    @SerializedName("wind_degree")
    @Expose
    var windDegree: Long? = null
    @SerializedName("wind_dir")
    @Expose
    var windDir: String? = null
    @SerializedName("pressure_mb")
    @Expose
    var pressureMb: Long? = null
    @SerializedName("pressure_in")
    @Expose
    var pressureIn: Double? = null
    @SerializedName("precip_mm")
    @Expose
    var precipMm: Float? = null
    @SerializedName("precip_in")
    @Expose
    var precipIn: Float? = null
    @SerializedName("humidity")
    @Expose
    var humidity: Long? = null
    @SerializedName("cloud")
    @Expose
    var cloud: Long? = null
    @SerializedName("feelslike_c")
    @Expose
    var feelslikeC: Double? = null
    @SerializedName("feelslike_f")
    @Expose
    var feelslikeF: Double? = null
    @SerializedName("vis_km")
    @Expose
    var visKm: Float? = null
    @SerializedName("vis_miles")
    @Expose
    var visMiles: Long? = null
    @SerializedName("uv")
    @Expose
    var uv: Long? = null
    @SerializedName("gust_mph")
    @Expose
    var gustMph: Double? = null
    @SerializedName("gust_kph")
    @Expose
    var gustKph: Double? = null

}
