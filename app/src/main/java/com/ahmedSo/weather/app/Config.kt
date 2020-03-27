package com.ahmedSo.weather.app

import java.text.SimpleDateFormat

object Config {

    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val API_APP_ID = "51c787774a2a27cdf1ed7d7abc053e2e"
    const val API_UNITS_FORMAT = "metric"
    val SERVER_DATE_FORMATTER = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
}