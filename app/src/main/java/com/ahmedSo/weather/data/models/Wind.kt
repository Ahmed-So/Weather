package com.ahmedSo.weather.data.models

import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

class Wind {
    @SerializedName("speed")
    val speed = 0f

    fun speedString(): String {
        return speed.roundToInt().toString()
    }
}