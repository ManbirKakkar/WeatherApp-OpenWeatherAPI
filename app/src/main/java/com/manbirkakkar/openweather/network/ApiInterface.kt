package com.manbirkakkar.openweather.network

import com.manbirkakkar.openweather.network.model.CurrentWeather
import com.manbirkakkar.openweather.network.model.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("data/2.5/weather")
    fun getCurrentWeatherForLocation(
        @Query("lat") latitude: String?,
        @Query("lon") longitude: String?,
        @Query("units") units: String?,
        @Query("appid") appId: String?
    ): Call<CurrentWeather>

    @GET("data/2.5/forecast")
    fun getWeatherForecastForLocation(
        @Query("lat") latitude: String?,
        @Query("lon") longitude: String?,
        @Query("cnt") cnt: String?,
        @Query("units") units: String?,
        @Query("appid") appId: String?
    ): Call<WeatherForecast>


}