package com.ahmedSo.weather.data.models

import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

class WeatherMainData {

    @SerializedName("temp_min")
    val minTemp = 0f

    @SerializedName("temp_max")
    val maxTemp = 0f

    fun minTemperature(): String {
        return minTemp.roundToInt().toString()
    }

    fun maxTemperature(): String {
        return maxTemp.roundToInt().toString()
    }
}