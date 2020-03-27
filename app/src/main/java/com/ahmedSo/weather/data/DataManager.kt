package com.ahmedSo.weather.data

import com.ahmedSo.weather.data.remote.WebAPI
import com.ahmedSo.weather.data.remote.responses.CityWeatherResponse
import com.ahmedSo.weather.data.remote.responses.LocationForecastResponse
import rx.Observable

object DataManager {
    private var webAPI: WebAPI? = null
    @JvmStatic
    fun init() {
        webAPI = WebAPI()
    }

    @JvmStatic
    fun getCityWeather(cityName: String?): Observable<CityWeatherResponse?>? {
        return webAPI!!.getCityWeather(cityName)
    }

    @JvmStatic
    fun getLocationForecast(latitude: Double, longitude: Double): Observable<LocationForecastResponse?>? {
        return webAPI!!.getLocationForecast(latitude, longitude)
    }
}