package com.ahmedSo.weather.data.remote

import com.ahmedSo.weather.app.Config
import com.ahmedSo.weather.data.remote.responses.CityWeatherResponse
import com.ahmedSo.weather.data.remote.responses.LocationForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface EndPoints {

    @GET("weather?appid=" + Config.API_APP_ID + "&units=" + Config.API_UNITS_FORMAT)
    fun getCityWeather(@Query("q") cityName: String?): Observable<CityWeatherResponse?>?

    @GET("forecast?appid=" + Config.API_APP_ID + "&units=" + Config.API_UNITS_FORMAT)
    fun getLocationForecast(
        @Query("lat") latitude: Double, @Query("lon") longitude: Double
    ): Observable<LocationForecastResponse?>?
}