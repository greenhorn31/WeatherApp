package com.test.part.weatherapp.domain.datamodels

import com.google.gson.annotations.SerializedName
import com.test.part.weatherapp.domain.datamodels.Main
import com.test.part.weatherapp.domain.datamodels.WeatherModel
import java.io.Serializable

class WeatherModelByLocation(@SerializedName("count")
                             val count: Int,
                             @SerializedName("list")
                             val weather: List<WeatherModel>,
                             @SerializedName("main")
                             val main: Main) : Serializable