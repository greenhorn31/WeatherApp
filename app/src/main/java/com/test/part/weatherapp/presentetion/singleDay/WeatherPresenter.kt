package com.test.part.weatherapp.presentetion.singleDay

interface WeatherPresenter {
    fun onAttach(view: SingleDayView)
    fun fetchWeather()
}