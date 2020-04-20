package com.manbirkakkar.openweather.network

import android.content.Context
import android.text.TextUtils
import com.manbirkakkar.openweather.R
import com.manbirkakkar.openweather.network.model.CurrentWeather
import com.manbirkakkar.openweather.network.model.WeatherForecast
import com.manbirkakkar.openweather.ui.home.model.CitySummaryModel
import com.manbirkakkar.openweather.ui.home.model.WeatherInfoModel
import com.manbirkakkar.openweather.utils.ConversionUtil
import com.manbirkakkar.openweather.utils.ConversionUtil.getFormattedDate
import com.manbirkakkar.openweather.utils.ConversionUtil.getFormattedTemperature
import com.manbirkakkar.openweather.utils.ConversionUtil.getWeatherIcon
import java.util.*

object CallWeatherAPI {
    fun createCitySummaryModel(
        context: Context?,
        currentWeather: CurrentWeather
    ): CitySummaryModel {
        val citySummaryModel = CitySummaryModel()
        if (currentWeather.weather != null && !currentWeather.weather.isEmpty()) {
            val weather = currentWeather.weather[0]
            citySummaryModel.weatherIcon = getWeatherIcon(context!!, weather.icon)
            citySummaryModel.weatherDescription =
                String.format("%s\n%s", weather.main, weather.description)
        }
        if (currentWeather.main != null) {
            citySummaryModel.temperature = getFormattedTemperature(
                context,
                currentWeather.main.temp
            )
        }
        if (currentWeather.sys != null && !TextUtils.isEmpty(currentWeather.sys.country)) {
            citySummaryModel.cityName = String.format(
                "%s, %s", currentWeather.name,
                currentWeather.sys.country
            )
        } else {
            citySummaryModel.cityName = currentWeather.name
        }
        return citySummaryModel
    }

    fun createWeatherInfoModels(
        context: Context,
        weatherForecast: WeatherForecast
    ): ArrayList<WeatherInfoModel> {
        val forecast: ArrayList<WeatherInfoModel> =
            ArrayList()
        if (weatherForecast.list != null) {
            for (list in weatherForecast.list) {
                val weatherInfoModel = WeatherInfoModel()
                weatherInfoModel.date = getFormattedDate(list.dt)
                if (list.weather != null && list.weather.size > 0) {
                    val weather = list.weather[0]
                    weatherInfoModel.weatherDescription = String.format(
                        "%s\n%s", weather.main,
                        weather.description
                    )
                    weatherInfoModel.weatherIcon =
                        getWeatherIcon(context, weather.icon)
                }
                if (list.main != null) {
                    weatherInfoModel.maxTemperature = context.getString(
                        R.string.max_temp,
                        getFormattedTemperature(context, list.main.tempMax)
                    )
                    weatherInfoModel.minTemperature = context.getString(
                        R.string.min_temp,
                        getFormattedTemperature(context, list.main.tempMin)
                    )
                    weatherInfoModel.humidity =
                        context.getString(R.string.humidity, list.main.humidity)
                }
                if (list.wind != null) {
                    weatherInfoModel.windSpeed = context.getString(
                        R.string.wind_speed, ConversionUtil
                            .getFormattedSpeed(context, list.wind.speed)
                    )
                }
                forecast.add(weatherInfoModel)
            }
        }
        return forecast
    }
}