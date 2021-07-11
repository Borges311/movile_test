package com.fernando.movilepaytest.data.repositories

import com.fernando.movilepaytest.feature.credit.service.CreditCardService
import com.fernando.movilepaytest.feature.credit.view.CreditCardState

class CreditCardRepository(
    private val service: CreditCardService) {

    suspend fun getCreditCardDetails(id: String?): CreditCardState {
        val response = service.requestCreditCardDetails(id)
        return if (response.isSuccessful){
            response.let { CreditCardState.CredicardStateSucess(it.body()) }
        }else{
            return CreditCardState.CredicardStateError(response.message())
        }
    }
}