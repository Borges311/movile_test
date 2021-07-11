package com.fernando.movilepaytest

import com.fernando.creditcard.feature.home.model.CreditCardInfo
import com.fernando.movilepaytest.data.repositories.CreditCardRepository
import com.fernando.movilepaytest.feature.credit.service.CreditCardService
import com.fernando.movilepaytest.feature.credit.view.CreditCardState
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CreditCardRepositoryTest {

    @MockK
    lateinit var creditCardRepository: CreditCardRepository

    @MockK
    lateinit var creditCardService: CreditCardService

    @MockK
    lateinit var credit: CreditCardInfo

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        creditCardRepository = CreditCardRepository(creditCardService)
        every { credit.cardName } returns  "•••• •••• •••• 8765"
        every { credit.cardName } returns "Teste Fulano Ciclano"
    }


    @Test
    fun `should return CreditCardState after receive Response`() = runBlocking<Unit> {
        coEvery { creditCardRepository.getCreditCardDetails("123") } returns CreditCardState.CredicardStateSucess(credit)
        coEvery { creditCardService.requestCreditCardDetails("123") } returns Response.success(credit)
        val result = creditCardRepository.getCreditCardDetails("123")
        Assert.assertTrue(result is CreditCardState.CredicardStateSucess)
    }
}