package com.fernando.movilepaytest.feature.statement.service

import com.fernando.movilepaytest.feature.statement.model.Statement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementApi {

    @GET("/android/interview/statement/{id}")
    suspend fun getStatement(@Path("id") id: String?): Response<Statement>
}