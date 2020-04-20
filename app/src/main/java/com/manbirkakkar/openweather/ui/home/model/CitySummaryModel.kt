package com.manbirkakkar.openweather.ui.home.model

import java.io.Serializable

class CitySummaryModel : Serializable {
    var cityName: String? = null
    var weatherIcon: String? = null
    var weatherDescription: String? = null
    var temperature: String? = null
}