package com.ahmedSo.weather.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedSo.weather.data.DataManager.getCityWeather
import com.ahmedSo.weather.data.remote.responses.CityWeatherResponse
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class SearchViewModel : ViewModel() {
    private val cityWeatherList: MutableList<CityWeatherResponse> = ArrayList()
    private val updateCityRecycler = MutableLiveData<Boolean>()
    private val showProgress = MutableLiveData(false)
    val citiesNames = MutableLiveData("")

    fun getCityWeatherList(): List<CityWeatherResponse> {
        return cityWeatherList
    }

    fun getUpdateCityRecycler(): LiveData<Boolean> {
        return updateCityRecycler
    }

    fun getShowProgress(): LiveData<Boolean> {
        return showProgress
    }

    val cities: Array<String>
        get() {
            val cities = citiesNames.value!!.split(",").toTypedArray()
            val result: MutableList<String> = ArrayList()
            for (city in cities) {
                if (!city.trim().isEmpty()) result.add(city.trim())
            }
            return result.toTypedArray()
        }

    fun checkData(cities: Array<String>): Boolean {
        return cities.size <= 7 && cities.size >= 3
    }

    fun search(cities: Array<String>) {
        showProgress.value = true
        cityWeatherList.clear()
        val requestObservableList: MutableList<Observable<CityWeatherResponse?>?> =
            ArrayList()
        for (city in cities) {
            requestObservableList.add(getCityWeather(city))
        }
        search(requestObservableList)
    }

    private fun search(requestObservableList: List<Observable<CityWeatherResponse?>?>) {
        Observable.merge(requestObservableList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Subscriber<CityWeatherResponse?>() {
                override fun onCompleted() {
                    updateCityRecycler.value = true
                    showProgress.value = false
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

                override fun onNext(cityWeatherResponse: CityWeatherResponse?) {
                    if (cityWeatherResponse?.isSuccessful!!) cityWeatherList.add(cityWeatherResponse) else Timber.e(
                        cityWeatherResponse.message
                    )
                }
            })
    }
}