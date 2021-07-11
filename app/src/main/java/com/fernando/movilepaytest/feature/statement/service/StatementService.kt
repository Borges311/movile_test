package com.fernando.movilepaytest.feature.statement.service

import com.fernando.movilepaytest.data.network.ServiceConector
import com.fernando.movilepaytest.data.network.URLs
import com.fernando.movilepaytest.feature.statement.model.Statement
import retrofit2.Response

class StatementService {

    suspend fun requestStatement(id: String?): Response<Statement> =
        ServiceConector.requestClient(
            URLs.MOVILE_BASE_URL,
            StatementApi::class.java
        ).getStatement(id)
}