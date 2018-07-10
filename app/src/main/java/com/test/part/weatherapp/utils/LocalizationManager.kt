package com.test.part.weatherapp.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v4.content.ContextCompat

class LocalizationManager(private val context: Context) {

    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null
    private var location: Location? = null

    init {
        if (locationManager == null) {
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
    }

    @SuppressLint("MissingPermission")
    fun getLocation(): Location? {
        locationManager?.let { locManager ->
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }
        }
        return location
    }

}