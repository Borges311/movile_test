package com.fernando.movilepaytest.feature.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fernando.movilepaytest.data.repositories.WidgetRepository
import com.fernando.movilepaytest.feature.main.constants.HomeConstants
import com.fernando.movilepaytest.feature.main.model.SimpleWidget
import com.fernando.movilepaytest.feature.main.view.HomeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val widgetRepository: WidgetRepository) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val _homeState = MutableLiveData<HomeState>()
    private val _widgetState = MutableLiveData<HomeState>()

    val homeState = _homeState
    val widgetState = _widgetState

    fun fetchHomeWidgets() {
        coroutineScope.launch {
            try {
                val result = widgetRepository.getWidgets()
                val list = mutableListOf<SimpleWidget>()
                when (result) {
                    is HomeState.HomeSuccessRequest -> {
                        result.data.forEach {
                            when (it.identifier) {
                                HomeConstants.HOME_HEADER_WIDGET -> {
                                    withContext(Dispatchers.Main) {
                                        _homeState.value =
                                            HomeState.WelcomeWidgetState(it.contentTitle)
                                    }
                                }
                                else -> {
                                    list.add(it)
                                }
                            }
                        }
                        withContext(Dispatchers.Main) {
                            _widgetState.value = HomeState.WidgetState(list)
                        }
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    widgetState.value = HomeState.HomeErrorRequest(ex.message)
                }
            }
        }
    }
}