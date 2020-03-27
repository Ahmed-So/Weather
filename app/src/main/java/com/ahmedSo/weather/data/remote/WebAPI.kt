package com.ahmedSo.weather.data.remote

import com.ahmedSo.weather.BuildConfig
import com.ahmedSo.weather.app.Config
import com.ahmedSo.weather.data.remote.responses.CityWeatherResponse
import com.ahmedSo.weather.data.remote.responses.LocationForecastResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import timber.log.Timber

class WebAPI {

    private var webServices: EndPoints? = null

    init {
        createApiConnection()
    }

    private fun createApiConnection() {
        webServices = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(EndPoints::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Timber.d(message);
                }
            })
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    fun getCityWeather(cityName: String?): Observable<CityWeatherResponse?>? {
        return webServices!!.getCityWeather(cityName)
    }

    fun getLocationForecast(
        latitude: Double, longitude: Double
    ): Observable<LocationForecastResponse?>? {
        return webServices!!.getLocationForecast(latitude, longitude)
    }
}