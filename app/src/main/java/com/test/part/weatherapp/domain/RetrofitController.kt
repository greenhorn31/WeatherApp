package com.test.part.weatherapp.domain

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitController(private val serverURL: String) {

    private var retrofit: Retrofit
    private var messageApi: MainWeatherApi

    init {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(serverURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        messageApi = retrofit.create(MainWeatherApi::class.java)
    }

    fun getApi(): MainWeatherApi {
        return messageApi
    }
}