package com.test.part.weatherapp.domain.repositories

import android.content.Context
import com.test.part.weatherapp.domain.datamodels.WeatherModel

interface CurrentWeatherRepository{
    fun getWeather(): List<WeatherModel>?
    fun updateWeather(weather: List<WeatherModel>)
    fun initialize(context: Context)
}