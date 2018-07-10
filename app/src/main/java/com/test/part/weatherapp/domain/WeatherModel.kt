package com.test.part.weatherapp.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherModel(@SerializedName("coord")
                   val coordinates: Coordinates,
                   @SerializedName("weather")
                   val weather: List<Weather>,
                   @SerializedName("main")
                   val main: Main) : Serializable

class Coordinates(@SerializedName("lon")
                  val longitude: String,
                  @SerializedName("lat")
                  val latitude: String)

class Weather(@SerializedName("id")
              val id: Int,
              @SerializedName("main")
              val main: String,
              @SerializedName("description")
              val description: String,
              @SerializedName("icon")
              val icon: String)

class Main(@SerializedName("temp")
           val temp: Float,
           @SerializedName("pressure")
           val pressure: Float,
           @SerializedName("humidity")
           val humidity: Float,
           @SerializedName("temp_min")
           val temp_min: Float,
           @SerializedName("temp_max")
           val temp_max: Float)


