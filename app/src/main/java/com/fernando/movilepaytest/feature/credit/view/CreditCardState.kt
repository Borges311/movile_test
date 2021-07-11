package com.fernando.movilepaytest.feature.credit.view

import com.fernando.creditcard.feature.home.model.CreditCardInfo

sealed class CreditCardState{
    data class CredicardStateSucess(val info: CreditCardInfo?) : CreditCardState()
    data class CredicardStateError(val errorMsg: String?) : CreditCardState()
}
