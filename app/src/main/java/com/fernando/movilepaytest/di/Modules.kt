package com.fernando.movilepaytest.di

import com.fernando.creditcard.feature.home.viewmodel.CreditCardViewModel
import com.fernando.movilepaytest.data.repositories.CreditCardRepository
import com.fernando.movilepaytest.data.repositories.StatementRepository
import com.fernando.movilepaytest.feature.main.service.WidgetService
import com.fernando.movilepaytest.data.repositories.WidgetRepository
import com.fernando.movilepaytest.feature.credit.service.CreditCardService
import com.fernando.movilepaytest.feature.main.viewmodel.HomeViewModel
import com.fernando.movilepaytest.feature.statement.service.StatementService
import com.fernando.movilepaytest.feature.statement.viewmodel.StatementViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val widgetModule = module {
    fun provideRepository(service: WidgetService): WidgetRepository = WidgetRepository(service)
    single { WidgetService() }
    single { provideRepository(get()) }
    viewModel { HomeViewModel(get()) }
}

val creditCardModule = module {
    fun provideRepository(service: CreditCardService): CreditCardRepository
    = CreditCardRepository(service)
    single { CreditCardService() }
    single { provideRepository(get()) }
    viewModel { CreditCardViewModel(get()) }
}

val statementModule = module {
    fun provideRepository(service: StatementService): StatementRepository
    = StatementRepository(service)
    single { StatementService() }
    single { provideRepository(get()) }
    viewModel { StatementViewModel(get()) }
}



