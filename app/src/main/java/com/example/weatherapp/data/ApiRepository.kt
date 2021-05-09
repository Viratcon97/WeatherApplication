package com.example.weatherapp.data

class ApiRepository(private val apiService: ApiService) {

    fun getTemperature(cityName : String) =
        apiService.GetTemperatureData(cityName)

    fun getForecast(cityName: String) =
        apiService.GetForecastData(cityName)
}