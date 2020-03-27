package com.ahmedSo.weather.ui.main.locationWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedSo.weather.data.DataManager.getLocationForecast
import com.ahmedSo.weather.data.remote.responses.LocationForecastResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

class LocationWeatherViewModel : ViewModel() {

    var latitude: Double? = null
        private set
    var longitude: Double? = null
        private set
    private val showProgress = MutableLiveData(false)
    private val locationForecastResponse = MutableLiveData<LocationForecastResponse>()

    fun setLocation(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
        locationForecast()
    }

    fun getShowProgress(): LiveData<Boolean> {
        return showProgress
    }

    fun getLocationForecastResponse(): LiveData<LocationForecastResponse> {
        return locationForecastResponse
    }

    private fun locationForecast() {
        showProgress.value = true
        getLocationForecast(latitude!!, longitude!!)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : Subscriber<LocationForecastResponse?>() {
                override fun onCompleted() {
                    showProgress.value = false
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

                override fun onNext(response: LocationForecastResponse?) {
                    if (response?.isSuccessful!!) locationForecastResponse.value = response
                }
            })
    }
}