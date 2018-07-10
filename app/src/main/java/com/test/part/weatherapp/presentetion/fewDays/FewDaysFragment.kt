package com.test.part.weatherapp.presentetion.fewDays

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.part.weatherapp.R

class FewDaysFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_few_days_fragment,container,false)
        return view
    }
    companion object {
        fun newInstance(): FewDaysFragment{
            return FewDaysFragment()
        }
    }
}