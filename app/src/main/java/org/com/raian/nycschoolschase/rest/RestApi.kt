package org.com.raian.nycschoolschase.rest

import org.com.raian.nycschoolschase.constants.GlobalConstants.Companion.weatherKey
import org.com.raian.nycschoolschase.rest.model.schools.SchoolsResultRestApi
import org.com.raian.nycschoolschase.rest.model.schools.ScoresResultRestApi
import org.com.raian.nycschoolschase.rest.model.weather.WeatherResultApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("s3k6-pzi2.json")
    fun getSchools(): Call<List<SchoolsResultRestApi>>

    @GET("f9bf-2cp4.json")
    fun getScores(): Call<List<ScoresResultRestApi>>

    @GET("current.json?key=$weatherKey")
    fun getWeatherFromCurrentLocation(@Query("q") query: String): Call<WeatherResultApi>
}