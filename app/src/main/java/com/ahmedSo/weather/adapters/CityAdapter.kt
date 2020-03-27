package com.ahmedSo.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmedSo.weather.R
import com.ahmedSo.weather.adapters.CityAdapter.CityHolder
import com.ahmedSo.weather.data.remote.responses.CityWeatherResponse
import com.ahmedSo.weather.databinding.ItemRecyclerSearchCityBinding
import com.squareup.picasso.Picasso

class CityAdapter(private val cityWeatherList: List<CityWeatherResponse>?) :
    RecyclerView.Adapter<CityHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        if (inflater == null) inflater = LayoutInflater.from(parent.context)
        return CityHolder(
            DataBindingUtil.inflate(inflater!!, R.layout.item_recycler_search_city, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.binding.city = cityWeatherList!![position]
        Picasso.get().load(cityWeatherList[position].description.iconURL)
            .into(holder.binding.ivItemSearchIcon)
    }

    override fun getItemCount(): Int {
        return cityWeatherList?.size ?: 0
    }

    inner class CityHolder(val binding: ItemRecyclerSearchCityBinding) :
        RecyclerView.ViewHolder(binding.root)
}