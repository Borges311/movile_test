package com.fernando.movilepaytest

import com.fernando.movilepaytest.data.repositories.WidgetRepository
import com.fernando.movilepaytest.feature.main.model.SimpleWidget
import com.fernando.movilepaytest.feature.main.model.widget.Widgets
import com.fernando.movilepaytest.feature.main.service.WidgetService
import com.fernando.movilepaytest.feature.main.view.HomeState
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class WidgetRepositoryTest {

    @MockK
    lateinit var widgetRepository: WidgetRepository

    @MockK
    lateinit var widgetService: WidgetService

    @MockK
    lateinit var mockWidgets: Widgets

    @MockK
    lateinit var simpleWidget: SimpleWidget

    @MockK
    lateinit var homeState: HomeState

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        widgetRepository = WidgetRepository(widgetService)

       every { simpleWidget.identifier } returns "HOME_HEADER_WIDGET"

       val mockList = arrayListOf<SimpleWidget>()
       mockList.add(simpleWidget)

       every { mockWidgets.widgetList } returns listOf(
            mockk{
                every { identifier } returns "HOME_HEADER_WIDGET"
                every { content.title } returns "Olá Fulano!"
                every { content.cardNumber } returns "•••• •••• •••• 8765"
                every { content.balance } returns null
                every { content.balance } returns null
                every { content.button } returns null
            }
        )

        homeState = HomeState.HomeSuccessRequest(mockList)
    }

    @Test
     fun `should return a list of SimpleWidgetClass`(){
        val result = widgetRepository.transformResponseToWidget(mockWidgets.widgetList)
        assertThat(result.size, `is`(1))
    }

    @Test
    fun `verify if Widget is succesfully converted`(){
        val result = widgetRepository.transformResponseToWidget(mockWidgets.widgetList)
        assertEquals(result[0].identifier, simpleWidget.identifier)
    }

    @Test
   fun `should return HomeState after receive Response`() = runBlocking<Unit> {
        val widgetList = arrayListOf<SimpleWidget>()
        widgetList.add(simpleWidget)
        coEvery { widgetService.requestHomeWidget() } returns Response.success(mockWidgets)
        val response = widgetRepository.getWidgets()
        assertTrue(response is HomeState.HomeSuccessRequest)
   }
}