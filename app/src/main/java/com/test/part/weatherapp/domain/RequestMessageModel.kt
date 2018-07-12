package com.test.part.weatherapp.domain


import com.test.part.weatherapp.domain.datamodels.WeatherModelByLocation
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RequestMessageModel(private val weatherApi: MainWeatherApi) {

    fun getTodaysWeather(lat: Double, lon: Double): Observable<WeatherModelByLocation> {
        return weatherApi.todaysWeather(lat, lon, "a28894f2156fab4dace6322bc558f60e")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}