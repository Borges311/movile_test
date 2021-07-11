package com.fernando.movilepaytest.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceConector {

    fun <T> requestClient(url: String, api: Class<T>) : T {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}