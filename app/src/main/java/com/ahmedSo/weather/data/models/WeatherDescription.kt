package com.ahmedSo.weather.data.models

import com.google.gson.annotations.SerializedName

class WeatherDescription {

    @SerializedName("description")
    val description: String? = null

    @SerializedName("icon")
    val icon: String? = null

    val iconURL: String
        get() = "https://openweathermap.org/img/wn/$icon@2x.png"
}