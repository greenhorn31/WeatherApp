package com.test.part.weatherapp.domain.repositories

import android.content.Context
import com.test.part.weatherapp.domain.datamodels.WeatherModel
import com.test.part.weatherapp.domain.datamodels.WeatherModelByLocation

interface CurrentWeatherStorage{
    fun getWeather(): List<WeatherModel>?
    fun updateWeather(weather: List<WeatherModel>)
    fun initialize(context: Context)
}