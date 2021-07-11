package com.fernando.movilepaytest.data.repositories

import com.fernando.movilepaytest.feature.main.service.WidgetService
import com.fernando.movilepaytest.feature.main.view.HomeState
import com.fernando.movilepaytest.feature.main.model.SimpleWidget
import com.fernando.movilepaytest.feature.main.model.widget.Widget

class WidgetRepository(private val service: WidgetService) {

  suspend fun getWidgets(): HomeState {
        val result = service.requestHomeWidget()
        return if (result.isSuccessful){
            HomeState.HomeSuccessRequest(transformResponseToWidget(result.body()?.widgetList))
        }else{
            HomeState.HomeErrorRequest(result.message())
        }
    }

    fun transformResponseToWidget(widgets: List<Widget>?): List<SimpleWidget> {
        val filteredList = arrayListOf<SimpleWidget>()
        widgets?.forEach {
            val widget = SimpleWidget(
                identifier = it.identifier,
                contentTitle = it.content.title,
                contentType = it.content.cardNumber,
                label = it.content.balance?.label,
                value = it.content.balance?.value,
                buttonTitle = it.content.button?.text,
                buttonAction = it.content.button?.action?.identifier,
                buttonContent = it.content.button?.action?.content?.typeId
            )
            filteredList.add(widget)
        }
        return filteredList
    }
}
