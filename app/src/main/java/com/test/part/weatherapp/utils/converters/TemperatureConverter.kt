package com.test.part.weatherapp.utils.converters

class TemperatureConverter(private val temp: Float) {

    fun kelvinToCelsius(): Float {
        return temp - 273.15f
    }
}