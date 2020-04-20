package com.manbirkakkar.openweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manbirkakkar.openweather.ui.home.model.CitySummaryModel
import com.manbirkakkar.openweather.ui.home.model.WeatherInfoModel
import com.manbirkakkar.openweather.utils.extensions.ScreenState

class HomeViewModel(private val weatherInterceptor: WeatherInterceptor) : ViewModel(),
    WeatherInterceptor.OnWeatherFinishedListener {

    private val _weatherState: MutableLiveData<ScreenState<WeatherState>> = MutableLiveData()
    val weatherState: LiveData<ScreenState<WeatherState>>
        get() = _weatherState

    private val _citySummaryModel: MutableLiveData<CitySummaryModel> = MutableLiveData()
    val citySummaryModel:LiveData<CitySummaryModel>
    get() = _citySummaryModel

    private val _forCastList: MutableLiveData<ArrayList<WeatherInfoModel>> = MutableLiveData()
    val forCastList:LiveData<ArrayList<WeatherInfoModel>>
        get() = _forCastList

    fun onCurrentWeather(
        lat: Double,
        lon: Double
    ) {
        _weatherState.value = ScreenState.Loading
        weatherInterceptor.getCurrentWeather(this, lat, lon)
    }

    fun onWeatherForeCast(
        lat: Double,
        lon: Double) {
        _weatherState.value = ScreenState.Loading
        weatherInterceptor.getWeatherForecast(this, lat, lon)
    }


    override fun getWeatherForecastSuccess(forecast: ArrayList<WeatherInfoModel>) {
        _forCastList.value = forecast
        _weatherState.value = ScreenState.Render(WeatherState.ForeCastSuccess)
    }

    override fun getCurrentWeatherSuccess(citySummaryModel: CitySummaryModel) {
        _citySummaryModel.value = citySummaryModel
        _weatherState.value = ScreenState.Render(WeatherState.WeatherSuccess)

    }

}

class HomeViewModelFactory(private val weatherInterceptor: WeatherInterceptor) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(weatherInterceptor) as T
    }
}