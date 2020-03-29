package com.ahmedSo.weather.ui.main.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    public var instantTaskExecutorRule = InstantTaskExecutorRule()
    private var searchViewModel = SearchViewModel()

    @Before
    fun setup() {
        searchViewModel = SearchViewModel()
    }

    @Test
    fun convertCitiesNamesStringToArray() {

        searchViewModel.citiesNames.value = ""
        Assert.assertEquals(searchViewModel.cities.size, 0)

        searchViewModel.citiesNames.value = "                "
        Assert.assertEquals(searchViewModel.cities.size, 0)

        searchViewModel.citiesNames.value = ", , , , , , , "
        Assert.assertEquals(searchViewModel.cities.size, 0)

        searchViewModel.citiesNames.value = "Dubai"
        Assert.assertEquals(searchViewModel.cities.size, 1)

        searchViewModel.citiesNames.value = "Dubai, "
        Assert.assertEquals(searchViewModel.cities.size, 1)

        searchViewModel.citiesNames.value = ", Dubai"
        Assert.assertEquals(searchViewModel.cities.size, 1)

        searchViewModel.citiesNames.value = "Dubai, , Cairo"
        Assert.assertEquals(searchViewModel.cities.size, 2)

        searchViewModel.citiesNames.value =
            "Dubai, Cairo, London, Tokyo, New York, Alex, Ajman, Paris, Roma, Moscow"
        Assert.assertEquals(searchViewModel.cities.size, 10)
    }

    @Test
    fun checkCitiesCount() {

        Assert.assertEquals(searchViewModel.checkData(arrayOf()), false)

        Assert.assertEquals(searchViewModel.checkData(arrayOf("", "")), false)

        Assert.assertEquals(searchViewModel.checkData(arrayOf("", "", "")), true)

        Assert.assertEquals(searchViewModel.checkData(arrayOf("", "", "", "", "")), true)

        Assert.assertEquals(searchViewModel.checkData(arrayOf("", "", "", "", "", "", "")), true)

        Assert.assertEquals(
            searchViewModel.checkData(arrayOf("", "", "", "", "", "", "", "")), false
        )
    }
}