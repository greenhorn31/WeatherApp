package com.test.part.weatherapp.utils

import android.app.ProgressDialog
import android.content.Context
import com.test.part.weatherapp.R

class ProgressDialogManager(private val context: Context) {

    private val progressBar: ProgressDialog = ProgressDialog(context)

    init {
        progressBar.setTitle(R.string.progress_bar_title)
        progressBar.setCancelable(false)
    }

    fun showLoading() {
        if (!progressBar.isShowing)
            progressBar.show()
    }

    fun hideLoading() {
        if (progressBar.isShowing)
            progressBar.hide()
    }

    fun destroy() {
        progressBar.dismiss()
    }
}