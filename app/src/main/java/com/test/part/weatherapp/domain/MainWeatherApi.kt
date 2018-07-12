package com.test.part.weatherapp.domain

import com.test.part.weatherapp.domain.datamodels.WeatherModelByLocation
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MainWeatherApi {

//    @GET("messages1.json")
//    fun messages(): Call<List<MessageModel>>
//
//    @GET("messages1.json")
//    fun messagesRx(): Observable<List<MessageModel>>

    @GET("find?")
    fun todaysWeather(@Query("lat") lat: Double,
                      @Query("lon") lon: Double,
                      @Query("appid") appid: String): Observable<WeatherModelByLocation>

//    http://api.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=10
//http://api.openweathermap.org/data/2.5/group?id=524901,703448,2643743&units=metric

    //for few days and wit htimestap
}