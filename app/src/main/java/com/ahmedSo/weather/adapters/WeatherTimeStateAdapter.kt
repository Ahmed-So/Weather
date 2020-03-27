package com.ahmedSo.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmedSo.weather.R
import com.ahmedSo.weather.adapters.WeatherTimeStateAdapter.WeatherTimeStateHolder
import com.ahmedSo.weather.data.models.WeatherTimeState
import com.ahmedSo.weather.databinding.ItemRecyclerWeatherTimeStateBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class WeatherTimeStateAdapter(private val weatherTimeStates: Array<WeatherTimeState>?) :
    RecyclerView.Adapter<WeatherTimeStateHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherTimeStateHolder {
        if (inflater == null) inflater = LayoutInflater.from(parent.context)
        return WeatherTimeStateHolder(
            DataBindingUtil.inflate(
                inflater!!, R.layout.item_recycler_weather_time_state, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherTimeStateHolder, position: Int) {
        holder.binding.weatherTimeState = weatherTimeStates!![position]
        holder.binding.tvWeatherTimeStateDate.setText(
            SimpleDateFormat("EEEE,  hh:mm a").format(weatherTimeStates[position].date)
        )
        Picasso.get().load(weatherTimeStates[position].description.iconURL)
            .into(holder.binding.ivWeatherTimeStateIcon)
    }

    override fun getItemCount(): Int {
        return weatherTimeStates?.size ?: 0
    }

    inner class WeatherTimeStateHolder(val binding: ItemRecyclerWeatherTimeStateBinding) :
        RecyclerView.ViewHolder(binding.root)
}