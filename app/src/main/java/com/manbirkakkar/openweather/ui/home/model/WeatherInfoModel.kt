package com.manbirkakkar.openweather.ui.home.model

import java.io.Serializable

class WeatherInfoModel : Serializable {
    var weatherIcon: String? = null
    var date: String? = null
    var maxTemperature: String? = null
    var minTemperature: String? = null
    var weatherDescription: String? = null
    var humidity: String? = null
    var windSpeed: String? = null
}