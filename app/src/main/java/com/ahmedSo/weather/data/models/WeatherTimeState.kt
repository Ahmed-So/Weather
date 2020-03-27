package com.ahmedSo.weather.data.models

import com.ahmedSo.weather.app.Config
import com.google.gson.annotations.SerializedName
import timber.log.Timber
import java.text.ParseException
import java.util.*

class WeatherTimeState {

    @SerializedName("main")
    val mainData: WeatherMainData? = null

    @SerializedName("weather")
    private val descriptionList: Array<WeatherDescription> = emptyArray()

    @SerializedName("wind")
    val wind: Wind? = null

    @SerializedName("dt_txt")
    private val dateString: String? = null

    var date: Date? = null
        get() {
            if (field == null) {
                try {
                    field = Config.SERVER_DATE_FORMATTER.parse(dateString)
                } catch (e: ParseException) {
                    Timber.e(e)
                }
            }
            return field
        }
        private set

    val description: WeatherDescription
        get() = descriptionList[0]
}