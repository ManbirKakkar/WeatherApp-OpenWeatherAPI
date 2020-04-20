package com.manbirkakkar.openweather.ui.home.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.pwittchen.weathericonview.WeatherIconView
import com.manbirkakkar.openweather.R


class WeatherAdapter(
    private val mContext: Context,
    private val studentList: ArrayList<WeatherInfoModel>
) :
    RecyclerView.Adapter<WeatherAdapter.SimpleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adap_weather, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        val item = studentList[position]

        val weatherInfoModel: WeatherInfoModel = studentList[position]
        holder.ivWeatherIcon!!.setIconResource(weatherInfoModel.weatherIcon)
        holder.tvDate!!.text = weatherInfoModel.date!!
        holder.tvMaxTemperature!!.text = weatherInfoModel.maxTemperature
        holder.tvMinTemperature!!.text = weatherInfoModel.minTemperature
        holder.tvWeatherInfo!!.text = weatherInfoModel.weatherDescription
        holder.tvHumidity!!.text = weatherInfoModel.humidity
        holder.tvWind!!.text = weatherInfoModel.windSpeed

    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivWeatherIcon: WeatherIconView = itemView.findViewById<View>(R.id.ivWeatherIcon) as WeatherIconView

        var tvDate: TextView = itemView.findViewById<View>(R.id.tvDate) as TextView
        var tvMaxTemperature: TextView = itemView.findViewById<View>(R.id.tvMaxTemperature) as TextView
        var tvMinTemperature: TextView = itemView.findViewById<View>(R.id.tvMinTemperature) as TextView
        var tvWeatherInfo: TextView = itemView.findViewById<View>(R.id.tvWeatherInfo) as TextView
        var tvWind: TextView = itemView.findViewById<View>(R.id.tvWind) as TextView
        var tvHumidity: TextView = itemView.findViewById<View>(R.id.tvHumidity) as TextView

    }

}
