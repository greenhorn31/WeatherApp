package com.test.part.weatherapp.presentetion.singleDay

import com.test.part.weatherapp.domain.datamodels.WeatherModel

interface SingleDayView {
    fun updateWeather(weather: List<WeatherModel>)
    fun showError(message: String)
}