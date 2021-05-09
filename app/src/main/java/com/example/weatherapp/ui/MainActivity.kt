package com.example.weatherapp.ui

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.MainActivityRecyclerAdapter
import com.example.weatherapp.common.Constants
import com.example.weatherapp.data.ApiRepository
import com.example.weatherapp.data.RetrofitBuilder
import com.example.weatherapp.viewmodel.MainActivityViewModel
import com.example.weatherapp.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var adapter: MainActivityRecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.GONE

        mainActivityViewModel = ViewModelProviders.of(
            this, MainActivityViewModelFactory(this, ApiRepository(RetrofitBuilder.apiService))
        ).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getTemperature("Bengaluru")

        mainActivityViewModel.isLoading.observe(this, Observer { isShowPrgress ->
            setLoading(isShowPrgress)
        })

        mainActivityViewModel.temperatureData.observe(this, Observer {

            if(it.cod == Constants.success_weather){
                tempTextView.text = it.main?.temp?.toInt().toString() + "\u00B0"
            }else {
                layout.setBackgroundColor(resources.getColor(R.color.errorBackground))
            }
        })

        mainActivityViewModel.getForecast("Bengaluru")

        mainActivityViewModel.forecastData.observe(this, Observer {

            linearLayoutManager = LinearLayoutManager(this)
            recyclerViewForecast.layoutManager = linearLayoutManager
            val resId: Int = R.anim.layout_animation_down_to_up
            val animation = AnimationUtils.loadLayoutAnimation(this, resId)
            recyclerViewForecast.setLayoutAnimation(animation)
            adapter = MainActivityRecyclerAdapter(it)
            recyclerViewForecast.adapter = adapter


        })
    }
    fun setLoading(isLoading: Boolean) {
        progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}