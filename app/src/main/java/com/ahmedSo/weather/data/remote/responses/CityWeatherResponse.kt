package com.ahmedSo.weather.data.remote.responses

import com.ahmedSo.weather.data.models.WeatherDescription
import com.ahmedSo.weather.data.models.WeatherMainData
import com.ahmedSo.weather.data.models.Wind
import com.google.gson.annotations.SerializedName

class CityWeatherResponse : BaseResponse() {

    @SerializedName("name")
    val name: String? = null

    @SerializedName("main")
    val mainData: WeatherMainData? = null

    @SerializedName("weather")
    private val descriptionList: Array<WeatherDescription> = emptyArray()

    @SerializedName("wind")
    val wind: Wind? = null

    val description: WeatherDescription
        get() = descriptionList[0]

}