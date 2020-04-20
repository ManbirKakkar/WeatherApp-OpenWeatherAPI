package com.manbirkakkar.openweather.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.manbirkakkar.openweather.R
import com.manbirkakkar.openweather.base.BaseActivity
import com.manbirkakkar.openweather.base.Constant
import com.manbirkakkar.openweather.base.Constant.Companion.DEFAULT_LAT
import com.manbirkakkar.openweather.base.Constant.Companion.DEFAULT_LON
import com.manbirkakkar.openweather.base.SessionClass
import com.manbirkakkar.openweather.base.checkLocationPermission
import com.manbirkakkar.openweather.ui.home.model.CitySummaryModel
import com.manbirkakkar.openweather.ui.home.model.WeatherAdapter
import com.manbirkakkar.openweather.utils.extensions.ScreenState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_current_temp.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(WeatherInterceptor(this))
        )[HomeViewModel::class.java]
        viewModel.weatherState.observe(::getLifecycle, ::updateUI)

        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu!!.findItem(R.id.action_units)

        item.title = when (Constant.UNITS_METRIC) {
            SessionClass.getUnit(this) -> "Change to imperial"
            else -> "Change to metric"

        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isChecked = !item.isChecked
        when (item.itemId) {
            R.id.action_units -> {

                if(SessionClass.getUnit(this) == Constant.UNITS_METRIC){
                  SessionClass.setUnits(this, Constant.UNITS_IMPERIAL)
                }else{
                    SessionClass.setUnits(this, Constant.UNITS_METRIC)
                }

                item.title = when (Constant.UNITS_METRIC) {
                    SessionClass.getUnit(this) -> "Change to imperial"
                    else -> "Change to metric"

                }

                 loadData()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }


    private fun updateUI(screenState: ScreenState<WeatherState>?) {
        when (screenState) {
            ScreenState.Loading -> progress.visibility = View.VISIBLE
            is ScreenState.Render -> processLoginState(screenState.renderState)
        }
    }

    private fun processLoginState(renderState: WeatherState) {
        progress.visibility = View.GONE
        when (renderState) {
            WeatherState.WeatherSuccess -> {
                showCitySummarySection(viewModel.citySummaryModel.value!!)
            }
            WeatherState.ForeCastSuccess -> {
                updateForeCastList()
            }

        }
    }

    private fun updateForeCastList() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        var weatherListAdapter = WeatherAdapter(this, viewModel.forCastList.value!!)
        recyclerView.adapter = weatherListAdapter
    }

    private fun loadData() {
        viewModel.onCurrentWeather(DEFAULT_LAT, DEFAULT_LON)
        viewModel.onWeatherForeCast(DEFAULT_LAT, DEFAULT_LON)
    }

    override fun onResume() {
        super.onResume()
        checkLocationPermission(this)
    }


    private fun showCitySummarySection(citySummaryModel: CitySummaryModel) {
        tvCityName.text = citySummaryModel.cityName
        ivWeatherIcon.setIconResource(citySummaryModel.weatherIcon)
        tvWeatherInfo.text = citySummaryModel.weatherDescription
        tvTemperature.text = citySummaryModel.temperature
    }
}
