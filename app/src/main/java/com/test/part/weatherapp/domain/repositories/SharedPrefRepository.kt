package com.test.part.weatherapp.domain.repositories

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.part.weatherapp.domain.datamodels.WeatherModel

class SharedPrefRepository : CurrentWeatherRepository {

    override fun getWeather(): List<WeatherModel>? {
        weather?.let {
            return it
        }
        val userString = preferences.getString(WEATHER, "")
        if (!TextUtils.isEmpty(userString)) {
            val turnsType = object : TypeToken<List<WeatherModel>>() {}.type
            val turns = Gson().fromJson<List<WeatherModel>>(userString, turnsType)
            weather = turns
        }
        return weather
    }

    override fun updateWeather(weather: List<WeatherModel>) {
        this.weather = weather
        preferences.edit().putString(WEATHER, Gson().toJson(weather)).apply()

    }

    private var weather: List<WeatherModel>? = null

    private lateinit var preferences: SharedPreferences

    override fun initialize(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    companion object {

        private const val PREFERENCES_NAME = "weather_preferences"
        private const val WEATHER = "weather"

        private var instance: SharedPrefRepository = SharedPrefRepository()

        fun getInstance(): CurrentWeatherRepository {
            return instance
        }

    }
}
