package com.ahmedSo.weather.data.remote.responses

import com.ahmedSo.weather.data.models.WeatherTimeState
import com.google.gson.annotations.SerializedName

class LocationForecastResponse : BaseResponse() {

    @SerializedName("list")
    val weatherTimeStates: Array<WeatherTimeState> = emptyArray()
}