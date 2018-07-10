package com.test.part.weatherapp.presentetion.singleDay

import android.location.Location
import com.test.part.weatherapp.presentetion.ProgressView
import com.test.part.weatherapp.domain.WeatherModel

interface SingleDayView {
    fun showWeather(weather: WeatherModel)
    fun showError(message: String)
    fun showCurrentLocation(location: Location)
}