package com.ahmedSo.weather.ui.main.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ahmedSo.weather.R
import com.ahmedSo.weather.adapters.CityAdapter
import com.ahmedSo.weather.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), SearchHandlers {
    private var mViewModel: SearchViewModel? = null
    private var binding: FragmentSearchBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding?.lifecycleOwner = this
        mViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding?.viewModel = mViewModel
        binding?.handlers = this
        binding?.rvSearchList?.adapter = CityAdapter(mViewModel!!.getCityWeatherList())
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel!!.getUpdateCityRecycler().observe(viewLifecycleOwner,
            Observer { update: Boolean? -> binding!!.rvSearchList.adapter!!.notifyDataSetChanged() }
        )
        mViewModel!!.getShowProgress().observe(viewLifecycleOwner,
            Observer { show: Boolean ->
                if (show) activity!!.window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                ) else activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        )
    }

    override fun search() {
        hideKeyboard()
        val citiesNames = mViewModel!!.cities
        if (mViewModel!!.checkData(citiesNames)) mViewModel!!.search(citiesNames) else Toast.makeText(
            context,
            getString(R.string.search_fragment_invalid_input_message),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun hideKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            val imm =
                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}