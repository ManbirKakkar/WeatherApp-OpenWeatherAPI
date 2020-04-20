package com.manbirkakkar.openweather.ui.home

import android.app.Activity
import android.content.Context
import com.manbirkakkar.openweather.base.Constant.Companion.OPEN_WEATHER_MAP_API_KEY
import com.manbirkakkar.openweather.base.SessionClass
import com.manbirkakkar.openweather.network.ApiClient
import com.manbirkakkar.openweather.network.ApiInterface
import com.manbirkakkar.openweather.network.CallWeatherAPI
import com.manbirkakkar.openweather.network.model.CurrentWeather
import com.manbirkakkar.openweather.network.model.WeatherForecast
import com.manbirkakkar.openweather.ui.home.model.CitySummaryModel
import com.manbirkakkar.openweather.ui.home.model.WeatherInfoModel
import com.manbirkakkar.openweather.utils.DebugLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherInterceptor(private val context: Context) {

    interface OnWeatherFinishedListener {
        fun getWeatherForecastSuccess(forecast: ArrayList<WeatherInfoModel>)
        fun getCurrentWeatherSuccess(citySummaryModel: CitySummaryModel)
    }

     fun getWeatherForecast(
        listener: OnWeatherFinishedListener,
        lat: Double,
        lon: Double) {

        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val getWeatherForecastForLocation = apiService.getWeatherForecastForLocation(
            lat.toString(),
            lon.toString(),
            "40",
            SessionClass.getUnit(context as Activity),
            OPEN_WEATHER_MAP_API_KEY
        )

        getWeatherForecastForLocation.enqueue(object : Callback<WeatherForecast?> {
            override fun onResponse(
                call: Call<WeatherForecast?>,
                response: Response<WeatherForecast?>
            ) {
                if (response.isSuccessful) {
                    val weatherForecast: WeatherForecast? = response.body()
                    val forecast: ArrayList<WeatherInfoModel> = CallWeatherAPI.createWeatherInfoModels(context, weatherForecast!!)
                    listener.getWeatherForecastSuccess(forecast)
                    //  weatherInfoAdapter.setItems(forecast)
                }
            }

            override fun onFailure(
                call: Call<WeatherForecast?>,
                throwable: Throwable
            ) {
                DebugLog.log("", "onFailure", throwable)
            }
        })
    }

    fun getCurrentWeather(listener: OnWeatherFinishedListener,
                                  lat: Double,
                                  lon: Double) {

        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val getCurrentWeatherForLocation = apiService.getCurrentWeatherForLocation(
            lat.toString(),
            lon.toString(),
            SessionClass.getUnit(context as Activity),
            OPEN_WEATHER_MAP_API_KEY
        )


        getCurrentWeatherForLocation.enqueue(object : Callback<CurrentWeather?> {
            override fun onResponse(
                call: Call<CurrentWeather?>,
                response: Response<CurrentWeather?>
            ) {
                if (response.isSuccessful) {
                    val currentWeather: CurrentWeather? = response.body()
                    val citySummaryModel: CitySummaryModel =
                        CallWeatherAPI.createCitySummaryModel(context, currentWeather!!)
                   // showCitySummarySection(citySummaryModel)
                    listener.getCurrentWeatherSuccess(citySummaryModel)
                }
            }

            override fun onFailure(
                call: Call<CurrentWeather?>,
                throwable: Throwable
            ) {
                DebugLog.log("", "onFailure", throwable)
            }
        })
    }
}