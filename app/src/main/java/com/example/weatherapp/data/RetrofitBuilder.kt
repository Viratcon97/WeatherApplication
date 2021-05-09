package com.example.weatherapp.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private var logging = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient.Builder()

    init{
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.connectTimeout(2, TimeUnit.MINUTES)
        httpClient.readTimeout(2,TimeUnit.MINUTES)
        httpClient.addInterceptor(HeaderInterceptor())
        httpClient.addInterceptor(logging)

    }
    private fun getRetrofit(): Retrofit {
        val jsonBuilder= GsonBuilder()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(jsonBuilder.setLenient().create()))
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}