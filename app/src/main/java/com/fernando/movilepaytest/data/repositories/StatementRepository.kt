package com.fernando.movilepaytest.data.repositories

import com.fernando.movilepaytest.feature.statement.service.StatementService
import com.fernando.movilepaytest.feature.statement.view.StatementState

class StatementRepository(
    private val service: StatementService){

   suspend fun getResponseStatement(id: String?): StatementState{
       val response = service.requestStatement(id)
       return if (response.isSuccessful){
           StatementState.StatementSuccess(response.body())
       }else{
           StatementState.StatementError(response.message())
       }
    }
}