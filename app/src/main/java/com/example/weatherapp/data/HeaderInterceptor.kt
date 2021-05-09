package com.example.weatherapp.data

import com.example.weatherapp.common.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)

    override fun intercept(chain: Interceptor.Chain): Response {

        val url: HttpUrl = chain.request().url.newBuilder().addQueryParameter("APPID", Constants.OPEN_WEATHER_API_KEY).
        addQueryParameter("units", "metric").build()
        return chain.proceed(chain.request().newBuilder().addHeader("Accept", "application/json").url(url).build())

    }
}