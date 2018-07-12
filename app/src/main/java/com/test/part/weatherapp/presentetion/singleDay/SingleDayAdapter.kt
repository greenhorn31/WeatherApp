package com.test.part.weatherapp.presentetion.singleDay

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.part.weatherapp.R
import com.test.part.weatherapp.domain.datamodels.WeatherModel
import com.test.part.weatherapp.utils.converters.TemperatureConverter

class SingleDayAdapter(private var weather: List<WeatherModel>)
    : RecyclerView.Adapter<SingleDayAdapter.WeatherViewHolder>() {

    public fun updateWeather(weather: List<WeatherModel>) {
        this.weather = weather
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return weather.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.tvCity.text = weather[position].name
        val temperature = TemperatureConverter(weather[position].main.temp).kelvinToCelsius()
        holder.tvTemp.text = temperature.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_signle_weather, parent, false)
        return WeatherViewHolder(view)
    }

    class WeatherViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvCity: TextView = view.findViewById(R.id.tvCity)
        val tvTemp: TextView = view.findViewById(R.id.tvTemp)
    }
}