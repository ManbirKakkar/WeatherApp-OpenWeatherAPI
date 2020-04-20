package com.manbirkakkar.openweather.ui.splash

import android.app.Activity
import android.content.Context
import com.manbirkakkar.openweather.base.Constant
import com.manbirkakkar.openweather.base.SessionClass
import com.manbirkakkar.openweather.utils.extensions.postDelayed


class SplashInterceptor(private val context: Context) {

    interface OnSplashFinishedListener {
        fun onLoggedIn()
    }

    fun checkLoggedIn(listener: OnSplashFinishedListener) {

        postDelayed(3000) {
            SessionClass.setUnits(context as Activity, Constant.UNITS_METRIC)
            listener.onLoggedIn()
        }

    }
}