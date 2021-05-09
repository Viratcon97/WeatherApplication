package com.example.weatherapp.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Forecast
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivityRecyclerAdapter(private val forecastList: Forecast) : RecyclerView.Adapter<ShowForecast>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowForecast {
       return ShowForecast(
           LayoutInflater.from(parent.context).inflate(
               R.layout.layout_forecast,parent,false)
       )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ShowForecast, position: Int) {

        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        for(i in 1 until forecastList.list!!.size){
            for (j in i+1 until forecastList.list.size){
                val presentDate = LocalDate.parse(forecastList.list[i].dtTxt.toString(),firstApiFormat)
                val nextDate = LocalDate.parse(forecastList.list[j].dtTxt.toString(),firstApiFormat)
                val presentDay = presentDate.dayOfWeek.toString()
                val nextDay = nextDate.dayOfWeek.toString()
                   holder.bindData(presentDay, forecastList.list[i].main!!.temp)

            }
            break
        }

    }

    override fun getItemCount(): Int {
        return 4
    }

}
class ShowForecast(view: View) : RecyclerView.ViewHolder(view) {

    val day = view.findViewById<TextView>(R.id.dayTextView)
    val temp = view.findViewById<TextView>(R.id.tempTextView)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindData(forecastList: String, temperature: Float) {
        day.text = forecastList
        temp.text = temperature.toString()
    }
}