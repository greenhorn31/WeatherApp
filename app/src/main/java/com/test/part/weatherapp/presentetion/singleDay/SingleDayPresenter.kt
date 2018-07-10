package com.test.part.weatherapp.presentetion.singleDay

import com.test.part.weatherapp.domain.MainWeatherApi
import com.test.part.weatherapp.domain.RequestMessageModel
import com.test.part.weatherapp.presentetion.ProgressView
import com.test.part.weatherapp.utils.LocalizationManager

class SingleDayPresenter(private val view: SingleDayView,
                         private val progressView: ProgressView,
                         private val weatherApi: MainWeatherApi,
                         private val locationManager: LocalizationManager) {

    init {
        weatherRetrofitRequest()
    }

    fun weatherRetrofitRequest() {
        locationManager.getLocation()?.let { location ->
            progressView.showLoading()
            RequestMessageModel(weatherApi).getTodaysWeather(location.latitude,
                    location.longitude)
                    .subscribe({ it ->
                        view.showWeather(it)
                        fetchAndShowLocation()
                        progressView.hideLoading()
                    }, { throwable ->
                        view.showError(throwable.localizedMessage)
                        progressView.hideLoading()
                    })
        }
    }

    private fun fetchAndShowLocation() {
        locationManager.getLocation()?.let {
            view.showCurrentLocation(it)
        }
    }

}