package com.example.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Forecast {
    @SerializedName("cod")
    @Expose
    val cod: String? = null

    @SerializedName("message")
    @Expose
    val message: Int? = null

    @SerializedName("cnt")
    @Expose
    val cnt: Int? = null

    @SerializedName("list")
    @Expose
    val list: java.util.List<List>? = null

    @SerializedName("city")
    @Expose
    val city: City? = null
}