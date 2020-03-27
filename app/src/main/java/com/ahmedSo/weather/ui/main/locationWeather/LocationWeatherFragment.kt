package com.ahmedSo.weather.ui.main.locationWeather

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ahmedSo.weather.R
import com.ahmedSo.weather.adapters.WeatherTimeStateAdapter
import com.ahmedSo.weather.data.remote.responses.LocationForecastResponse
import com.ahmedSo.weather.databinding.FragmentLocationWeatherBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

class LocationWeatherFragment : Fragment() {

    private val PERMISSION_LOCATION = 1001
    private var mViewModel: LocationWeatherViewModel? = null
    private var binding: FragmentLocationWeatherBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_location_weather, container, false)
        binding?.lifecycleOwner = this
        mViewModel = ViewModelProvider(this).get(LocationWeatherViewModel::class.java)
        binding?.viewModel = mViewModel
        lastLocation()
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel!!.getLocationForecastResponse().observe(
            viewLifecycleOwner, Observer { response: LocationForecastResponse? ->
                binding!!.rvCurrentWeatherList.adapter =
                    WeatherTimeStateAdapter(mViewModel!!.getLocationForecastResponse().value!!.weatherTimeStates)
            }
        )
    }

    private fun lastLocation() {
        if (mViewModel!!.latitude != null) return

        if (checkPermissions()) {
            LocationServices.getFusedLocationProviderClient(activity!!).lastLocation
                .addOnCompleteListener { task: Task<Location?> ->
                    val location = task.result
                    if (location != null) {
                        mViewModel!!.setLocation(location.latitude, location.longitude)
                    } else {
                        Toast.makeText(
                            context,
                            getString(R.string.current_weather_fragment_fetch_location_failed_message),
                            Toast.LENGTH_SHORT
                        ).show()
                        activity!!.onBackPressed()
                    }
                }
        } else {
            requestPermissions()
        }
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context!!, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context!!, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
            ), PERMISSION_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_LOCATION) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                lastLocation()
            } else
                activity!!.onBackPressed()
        }
    }
}