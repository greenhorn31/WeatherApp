package com.test.part.weatherapp.presentetion

import android.Manifest
import android.app.Fragment
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.design.widget.TabLayout.Tab
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.LocalBroadcastManager

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.test.part.weatherapp.*
import com.test.part.weatherapp.utils.ProgressDialogManager
import com.test.part.weatherapp.domain.RetrofitController
import com.test.part.weatherapp.domain.repositories.SharedPrefRepository
import com.test.part.weatherapp.presentetion.fewDays.FewDaysFragment
import com.test.part.weatherapp.presentetion.singleDay.SingleDayFragment
import com.test.part.weatherapp.utils.LocalizationManager

class MainActivity : AppCompatActivity(), MainView, ProgressView {

    private val SERVER_URL: String = "https://rawgit.com/startandroid/data/master/messages/"
    private val WEATHER_SERVER_URL: String = "http://api.openweathermap.org/data/2.5/"
    private lateinit var presenter: MainPresenter
    private lateinit var progressBar: ProgressDialogManager
    private lateinit var tabLayout: TabLayout

    private lateinit var localizationManager: LocalizationManager
    private val LOCATION_PERMISSION_REQUEST_CODE = 1134


    private val retrofitController = RetrofitController(WEATHER_SERVER_URL)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            initUi()
            SharedPrefRepository.getInstance().initialize(this)
            localizationManager = LocalizationManager(this)
            presenter = MainPresenter(this, retrofitController)
            checkLocationPermission()
            val fragment = SingleDayFragment.newInstance(retrofitController.getApi())
            addFragment(fragment, false)
        }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // todo ?
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // todo ?
                }
            }
        }
    }

    private fun initUi() {
        progressBar = ProgressDialogManager(this)
        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.addTab(tabLayout.newTab().setText("Today's weather"))
        tabLayout.addTab(tabLayout.newTab().setText("Weak weather"))

        Log.d("ASD", tabLayout.tabCount.toString())

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: Tab) {}

            override fun onTabUnselected(tab: Tab?) {}

            override fun onTabSelected(tab: Tab) {
                if (tab.position == 0) {
                    val fragment = SingleDayFragment.newInstance(retrofitController.getApi())
                    addFragment(fragment, false)
                }
                if (tab.position == 1) {
                    val fragment = FewDaysFragment.newInstance()
                    addFragment(fragment, false)
                }
            }
        })
    }

    private fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainer, fragment)
        if (addToBackStack)
            ft.addToBackStack(fragment::class.java.simpleName)
        ft.commit()
    }


    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            this.finish()
        }
    }

    override fun hideLoading() {
        progressBar.hideLoading()
    }

    override fun showLoading() {
        progressBar.showLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        progressBar.destroy()
    }
}
