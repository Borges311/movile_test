package com.fernando.movilepaytest.feature.credit.service

import com.fernando.movilepaytest.data.network.ServiceConector
import com.fernando.movilepaytest.data.network.URLs

class CreditCardService{

   suspend fun requestCreditCardDetails(id: String?)  =
        ServiceConector.requestClient(
            URLs.MOVILE_BASE_URL, CreditCardApi::class.java
        ).getCreditCardDetails(id)
}