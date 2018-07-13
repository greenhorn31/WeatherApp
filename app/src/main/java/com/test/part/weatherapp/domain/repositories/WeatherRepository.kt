package com.test.part.weatherapp.domain.repositories

import com.test.part.weatherapp.domain.MainWeatherApi
import com.test.part.weatherapp.domain.datamodels.WeatherModel
import io.reactivex.Observable

class WeatherRepository(
        private val mainWeatherApi: MainWeatherApi,
        private val weatherStorage: CurrentWeatherStorage) : Weather {

    private val appID = "lol"

    override fun weather(lat: Double, lon: Double): Observable<List<WeatherModel>> {
        return this.mainWeatherApi
                .todaysWeather(lat, lon, appID)
                .flatMap {
                    weatherStorage.updateWeather(it.weather)
                    return@flatMap Observable.just(it.weather)
                }
                .onErrorReturn {
                    it.printStackTrace()
                    this.weatherStorage.getWeather()
                }
    }
}