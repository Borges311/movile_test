package com.fernando.movilepaytest.feature.main.service

import com.fernando.movilepaytest.feature.main.model.widget.Widgets
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface WidgetApi {

    @GET("android/interview/home")
    suspend fun getHomeWidget(): Response<Widgets>
}