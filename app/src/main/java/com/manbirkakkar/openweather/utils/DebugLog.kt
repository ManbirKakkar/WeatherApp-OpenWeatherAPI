package com.manbirkakkar.openweather.utils

import android.util.Log
import com.manbirkakkar.openweather.BuildConfig

object DebugLog {
    fun log(tag: String?, message: String?, e: Throwable?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message, e)
        }
    }

    fun log(tag: String?, message: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }
}