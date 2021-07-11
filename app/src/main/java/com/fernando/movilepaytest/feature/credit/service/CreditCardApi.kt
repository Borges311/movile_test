package com.fernando.movilepaytest.feature.credit.service

import com.fernando.creditcard.feature.home.model.CreditCardInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CreditCardApi {

    @GET("/android/interview/card/{id}")
    suspend fun getCreditCardDetails(@Path("id") id: String?) : Response<CreditCardInfo>
}