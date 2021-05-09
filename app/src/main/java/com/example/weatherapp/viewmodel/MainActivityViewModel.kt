package com.example.weatherapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.ApiRepository
import com.example.weatherapp.model.Forecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.example.weatherapp.model.WeatherResponse

class MainActivityViewModel(private val context: Context,private val apiRepository: ApiRepository) : ViewModel() {

    var disposable: CompositeDisposable = CompositeDisposable()
    var temperatureData : MutableLiveData<WeatherResponse> = MutableLiveData()
    var forecastData : MutableLiveData<Forecast> = MutableLiveData()
    var isLoading:MutableLiveData<Boolean> = MutableLiveData()

    fun getTemperature(cityName : String){
        isLoading.value = true
        disposable.let {
            it.add(apiRepository.getTemperature(cityName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    isLoading.value = false
                    temperatureData.value = it
                })
        }
    }

    fun getForecast(cityName: String){
        disposable.let {
            it.add(apiRepository.getForecast(cityName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{
                    forecastData.value = it
                })
        }
    }


}