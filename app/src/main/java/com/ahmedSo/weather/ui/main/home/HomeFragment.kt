package com.ahmedSo.weather.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ahmedSo.weather.R
import com.ahmedSo.weather.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), HomeHandlers {

    private var mViewModel: HomeViewModel? = null
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = mViewModel
        binding.handlers = this
        return binding.root
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun openSearch() {
        navController!!.navigate(R.id.homeFragment_to_searchFragment)
    }

    override fun openCurrentWeather() {
        navController!!.navigate(R.id.homeFragment_to_currentWeatherFragment)
    }
}