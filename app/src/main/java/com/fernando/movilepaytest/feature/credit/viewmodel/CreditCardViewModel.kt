package com.fernando.creditcard.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fernando.movilepaytest.data.repositories.CreditCardRepository
import com.fernando.movilepaytest.feature.credit.view.CreditCardState
import com.fernando.movilepaytest.feature.main.view.HomeState
import kotlinx.coroutines.*
import java.lang.Exception

class CreditCardViewModel(
    private val repository: CreditCardRepository): ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val _creditState = MutableLiveData<CreditCardState>()
    val creditCardState = _creditState

    fun getCreditDetails(id: String?) {
        coroutineScope.launch {
            try {
                val result = repository.getCreditCardDetails(id)
                withContext(Dispatchers.Main) {
                    creditCardState.value = result
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    creditCardState.value = CreditCardState.CredicardStateError("")
                }
            }
        }
    }
}