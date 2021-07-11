package com.fernando.movilepaytest.feature.main.view

import com.fernando.movilepaytest.feature.main.model.SimpleWidget

sealed class HomeState{

    data class HomeSuccessRequest(val data: List<SimpleWidget>): HomeState()
    data class HomeErrorRequest(val exception: String?): HomeState()

    data class WelcomeWidgetState(val homeWelcome: String?): HomeState()
    data class WidgetState(val widgetList: List<SimpleWidget>): HomeState()

}
