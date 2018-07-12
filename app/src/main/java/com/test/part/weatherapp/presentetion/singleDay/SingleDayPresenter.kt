package com.test.part.weatherapp.presentetion.singleDay

import android.util.Log
import com.test.part.weatherapp.domain.MainWeatherApi
import com.test.part.weatherapp.domain.RequestMessageModel
import com.test.part.weatherapp.domain.datamodels.WeatherModel
import com.test.part.weatherapp.domain.repositories.CurrentWeatherRepository
import com.test.part.weatherapp.presentetion.ProgressView
import com.test.part.weatherapp.utils.LocalizationManager

class SingleDayPresenter(private val view: SingleDayView,
                         private val progressView: ProgressView,
                         private val weatherApi: MainWeatherApi,
                         private val locationManager: LocalizationManager,
                         private val weatherRepository: CurrentWeatherRepository) {

    init {
        val weather = weatherRepository.getWeather()
        if (weather == null) {
            weatherRetrofitRequest()
        } else {
            showWeather(weather)
        }
    }

    fun weatherRetrofitRequest() {
        locationManager.getLocation()?.let { location ->
            progressView.showLoading()
            RequestMessageModel(weatherApi).getTodaysWeather(location.latitude,
                    location.longitude)
                    .subscribe({ it ->
                        //                        view.updateWeather(it.weather)
                        saveAndShowWeather(it.weather)
                        progressView.hideLoading()
                    }, { throwable ->
                        view.showError(throwable.localizedMessage)
                        progressView.hideLoading()
                    })
        }
    }

    private fun showWeather(weather: List<WeatherModel>) {
        view.updateWeather(weather)
    }

    private fun saveAndShowWeather(weather: List<WeatherModel>) {
        weatherRepository.updateWeather(weather)
        weatherRepository.getWeather()?.let {
            view.updateWeather(it)
        }
    }

}