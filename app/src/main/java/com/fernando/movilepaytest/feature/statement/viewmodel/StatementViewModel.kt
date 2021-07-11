package com.fernando.movilepaytest.feature.statement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fernando.movilepaytest.data.repositories.StatementRepository
import com.fernando.movilepaytest.feature.statement.view.StatementState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StatementViewModel(
    private val repository: StatementRepository
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val _statementState = MutableLiveData<StatementState>()
    val state = _statementState

    fun getStatement(id: String?) {
        coroutineScope.launch {
            try {
                val result = repository.getResponseStatement(id)
                _statementState.postValue(result)
            } catch (ex: Exception) {
                withContext(Dispatchers.Main){
                    _statementState.value = StatementState.StatementError(ex.message)
                }
            }
        }
    }
}