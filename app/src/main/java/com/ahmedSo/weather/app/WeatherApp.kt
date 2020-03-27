package com.ahmedSo.weather.app

import android.app.Application
import com.ahmedSo.weather.BuildConfig
import com.ahmedSo.weather.data.DataManager
import timber.log.Timber
import timber.log.Timber.DebugTree

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DataManager.init()
        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }
}