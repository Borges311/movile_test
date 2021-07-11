package com.fernando.movilepaytest.feature.main.service

import com.fernando.movilepaytest.data.network.ServiceConector
import com.fernando.movilepaytest.data.network.URLs
import com.fernando.movilepaytest.feature.main.model.widget.Widgets
import retrofit2.Response

class WidgetService {

    suspend fun requestHomeWidget(): Response<Widgets> =
        ServiceConector.requestClient(
            URLs.MOVILE_BASE_URL, WidgetApi::class.java
        ).getHomeWidget()
}