package com.test.part.weatherapp.domain


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.flowable.FlowableDoAfterNext
import io.reactivex.schedulers.Schedulers


class RequestMessageModel(private val weatherApi: MainWeatherApi) {
    fun getMessages(): Observable<List<MessageModel>> {
        return weatherApi.messagesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTodaysWeather(lat: Double, lon: Double): Observable<WeatherModel> {
        return weatherApi.todaysWeather(lat, lon, "a28894f2156fab4dace6322bc558f60e")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}