package com.test.part.weatherapp.presentetion.singleDay

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.test.part.weatherapp.R
import com.test.part.weatherapp.domain.datamodels.WeatherModel

class SingleDayFragment : Fragment(), SingleDayView {

    private lateinit var presenter: WeatherPresenter
    private lateinit var updateButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SingleDayAdapter


    companion object {
        fun newInstance(presenter: WeatherPresenter): SingleDayFragment {
            val fragment = SingleDayFragment()
            fragment.presenter = presenter
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.presenter.onAttach(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_single_day_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecycler()
        initListeners()
        presenter.fetchWeather()
    }

    private fun initView() {
        updateButton = activity.findViewById(R.id.buttonUpdateWeather)
        recyclerView = activity.findViewById(R.id.recyclerView)
    }

    private fun initRecycler() {
        adapter = SingleDayAdapter(listOf())
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    private fun initListeners() {
        updateButton.setOnClickListener {
            presenter.fetchWeather()
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this.activity.applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun updateWeather(weather: List<WeatherModel>) {
        adapter.updateWeather(weather)
    }

}