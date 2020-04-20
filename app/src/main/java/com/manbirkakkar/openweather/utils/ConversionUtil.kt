package com.manbirkakkar.openweather.utils

import android.app.Activity
import android.content.Context
import com.manbirkakkar.openweather.R
import com.manbirkakkar.openweather.base.Constant.Companion.CELSIUS_SYMBOL
import com.manbirkakkar.openweather.base.Constant.Companion.FAHRENHEIT_SYMBOL
import com.manbirkakkar.openweather.base.Constant.Companion.SPEED_UNIT_IMPERIAL
import com.manbirkakkar.openweather.base.Constant.Companion.SPEED_UNIT_METRIC
import com.manbirkakkar.openweather.base.SessionClass
import com.manbirkakkar.openweather.utils.DebugLog.log
import java.math.RoundingMode
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


object ConversionUtil {
    private val TAG: String = ConversionUtil::class.java.simpleName
    fun getFormattedDate(unixTimestamp: Int): String {
        try {
            val date = Date(unixTimestamp * 1000L)
            val sdf =
                SimpleDateFormat("EEE, MMM d, HH:mm", Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(date)
        } catch (e: Exception) {
            log(TAG, "getFormattedDate", e)
        }
        return ""
    }

    fun getFormattedTemperature(
        context: Context?,
        value: Double
    ): String {
        val units = getUnitsFormat(context)
        val symbol: String = if ("metric".equals(
                units,
                ignoreCase = true
            )
        ) CELSIUS_SYMBOL else FAHRENHEIT_SYMBOL
        try {
            val nf = NumberFormat.getInstance()
            nf.maximumFractionDigits = 0
            nf.roundingMode = RoundingMode.HALF_UP
            return String.format("%s %s", nf.format(value), symbol)
        } catch (e: Exception) {
            log(TAG, "getFormattedValue", e)
        }
        return String.format("%s %s", value.toInt(), symbol)
    }

    fun getFormattedSpeed(context: Context?, value: Double): String {
        val units = getUnitsFormat(context)
        val symbol: String = if ("metric".equals(
                units,
                ignoreCase = true
            )
        ) SPEED_UNIT_METRIC else SPEED_UNIT_IMPERIAL
        try {
            val nf = NumberFormat.getInstance()
            nf.maximumFractionDigits = 2
            nf.roundingMode = RoundingMode.HALF_UP
            return String.format("%s %s", nf.format(value), symbol)
        } catch (e: Exception) {
            log(TAG, "getFormattedValue", e)
        }
        return String.format(Locale.getDefault(), "%s %s", value, symbol)
    }

    private fun getUnitsFormat(context: Context?): String {
        return SessionClass.getUnit(context as Activity)
    }

    fun getWeatherIcon(context: Context, icon: String?): String {
        val iconRes: Int = when (icon) {
            "01d" -> R.string.wi_day_sunny
            "02d" -> R.string.wi_cloudy_gusts
            "03d" -> R.string.wi_cloud_down
            "04d" -> R.string.wi_cloudy
            "09d" -> R.string.wi_day_showers
            "10d" -> R.string.wi_day_rain_mix
            "11d" -> R.string.wi_day_thunderstorm
            "13d" -> R.string.wi_day_snow
            "50d" -> R.string.wi_day_fog
            "01n" -> R.string.wi_night_clear
            "02n" -> R.string.wi_night_cloudy
            "03n" -> R.string.wi_night_cloudy_gusts
            "04n" -> R.string.wi_night_cloudy
            "09n" -> R.string.wi_night_showers
            "10n" -> R.string.wi_night_cloudy_gusts
            "11n" -> R.string.wi_night_rain
            "13n" -> R.string.wi_night_snow
            "50n" -> R.string.wi_night_fog
            else -> R.string.wi_na
        }
        return context.getString(iconRes)
    }
}