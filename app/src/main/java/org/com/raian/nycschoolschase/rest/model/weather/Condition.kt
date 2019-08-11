package org.com.raian.nycschoolschase.rest.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Condition {

    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("icon")
    @Expose
    var icon: String? = null
    @SerializedName("code")
    @Expose
    var code: Long? = null

}
