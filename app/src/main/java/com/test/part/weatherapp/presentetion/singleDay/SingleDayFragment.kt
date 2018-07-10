package com.test.part.weatherapp.presentetion.singleDay

import android.app.Fragment
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.test.part.weatherapp.*
import com.test.part.weatherapp.domain.MainWeatherApi
import com.test.part.weatherapp.domain.WeatherModel
import com.test.part.weatherapp.presentetion.ProgressView
import com.test.part.weatherapp.utils.LocalizationManager
import kotlinx.android.synthetic.main.fragment_single_day_fragment.*

class SingleDayFragment : Fragment(), SingleDayView {

    private lateinit var presenter: SingleDayPresenter
    private lateinit var weatherApi: MainWeatherApi
    private lateinit var updateButton: Button
    private lateinit var progressView: ProgressView

    companion object {
        fun newInstance(weatherApi: MainWeatherApi): SingleDayFragment {
            val fragment = SingleDayFragment()
            fragment.weatherApi = weatherApi
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        progressView = context as ProgressView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_single_day_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
        presenter = SingleDayPresenter(this, progressView, weatherApi, LocalizationManager(activity))
    }

    private fun initView() {
        updateButton = activity.findViewById(R.id.buttonUpdateWeather)
    }

    private fun initListeners() {
        updateButton.setOnClickListener {
            presenter.weatherRetrofitRequest()
        }
    }

    override fun showCurrentLocation(location: Location) {
        var text: StringBuilder = StringBuilder(tvWeather.text)
        text.append("current location: " + location.longitude + " " + location.latitude)
        tvWeather.text = text

    }

    override fun showError(message: String) {
        Toast.makeText(this.activity.applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun showWeather(weather: WeatherModel) {
        tvWeather.text = "Weather latitude: " + weather.coordinates.latitude + " longitude: " + weather.coordinates.longitude + " " + weather.weather[0].main + " "
    }

//    override fun showLoading() {
//        progressView.showLoading()
//    }
//
//    override fun hideLoading() {
//        progressView.hideLoading()
//    }

}