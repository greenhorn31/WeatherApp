package com.test.part.weatherapp.presentetion

import android.util.Log
import com.test.part.weatherapp.domain.MessageModel
import com.test.part.weatherapp.domain.RequestMessageModel
import com.test.part.weatherapp.domain.RetrofitController
import com.test.part.weatherapp.domain.MainWeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: MainView, private val retrofit: RetrofitController) {

    init {
        // getData(retrofit.getApi())
        //getDataWithRx(retrofit.getApi())
       // weatherRetrofitRequest(retrofit.getApi())
    }

    private fun weatherRetrofitRequest(dataApi: MainWeatherApi) {
//        RequestMessageModel(dataApi).getTodaysWeather()
//                .subscribe({ it ->
//                    Log.d("ASD", "data loaded successfully t " + it.coordinates.longitude)//+ it.coordinates.latitude + " " + it.coordinates.longitude)
//                }, { throwable ->
//                    Log.d("ASD", "fail loading data " + throwable.toString())
//                })
    }

    private fun getDataWithRx(dataApi: MainWeatherApi) {
        RequestMessageModel(dataApi).getMessages()
                .subscribe({ it ->
                    Log.d("ASD", "data loaded successfully " + it.size)
                }, { throwable ->
                    Log.d("ASD", "fail loading data " + throwable.toString())
                })
    }


    private fun getData(dataApi: MainWeatherApi) {
        val messages: Call<List<MessageModel>> = dataApi.messages()
        messages.enqueue(object : Callback<List<MessageModel>> {

            override fun onFailure(call: Call<List<MessageModel>>?, t: Throwable?) {
                Log.d("ASD", "fail loading data " + t.toString())
            }

            override fun onResponse(call: Call<List<MessageModel>>?, response: Response<List<MessageModel>>) {
                if (response.isSuccessful) {
                    Log.d("ASD", "data loaded successfully " + response.body()?.size)
                } else {
                    Log.d("ASD", "data loaded successfully " + response.code())
                }

            }

        })
    }

}