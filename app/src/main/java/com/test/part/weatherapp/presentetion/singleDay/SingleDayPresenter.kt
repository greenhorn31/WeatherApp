package com.test.part.weatherapp.presentetion.singleDay

import com.test.part.weatherapp.domain.repositories.Weather
import com.test.part.weatherapp.presentetion.ProgressView
import com.test.part.weatherapp.utils.LocalizationManager
import io.reactivex.android.schedulers.AndroidSchedulers

class SingleDayPresenter(private val progressView: ProgressView,
                         private val weather: Weather,
                         private val locationManager: LocalizationManager) : WeatherPresenter {

    private var view: SingleDayView? = null

    override fun onAttach(view: SingleDayView) {
        this.view = view
    }

    override fun fetchWeather() {
        locationManager.getLocation()?.let { location ->
            progressView.showLoading()
            this.weather.weather(location.latitude, location.longitude)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ it ->
                        view?.updateWeather(it)
                        progressView.hideLoading()
                    }, { throwable ->
                        view?.showError(throwable.localizedMessage)
                        progressView.hideLoading()
                    })
        }
    }
}