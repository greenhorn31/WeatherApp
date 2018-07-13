package com.test.part.weatherapp.domain.repositories

import com.test.part.weatherapp.domain.datamodels.WeatherModel
import io.reactivex.Observable

interface Weather {
    fun weather(lat: Double, lon: Double): Observable<List<WeatherModel>>
}