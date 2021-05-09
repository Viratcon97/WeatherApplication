package com.example.weatherapp.data

import com.example.weatherapp.model.Forecast
import com.example.weatherapp.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("weather")
    fun GetTemperatureData(@Query("q") cityName : String): Observable<WeatherResponse>

    @POST("forecast")
    fun GetForecastData(@Query("q") cityName : String): Observable<Forecast>


}