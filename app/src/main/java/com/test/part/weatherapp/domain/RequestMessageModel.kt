package com.test.part.weatherapp.domain


import com.test.part.weatherapp.domain.datamodels.WeatherModelByLocation
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RequestMessageModel(private val weatherApi: MainWeatherApi): MainWeatherApi {

    //todo extract into BUILD CONFIG
    private val appID = "a28894f2156fab4dace6322bc558f60e"

    override fun todaysWeather(lat: Double, lon: Double, appid: String): Observable<WeatherModelByLocation> {
        return weatherApi.todaysWeather(lat, lon, appID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}