package org.com.raian.nycschoolschase.rest.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResultApi {

    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("current")
    @Expose
    var current: Current? = null

}
