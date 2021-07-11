package com.fernando.movilepaytest

import com.fernando.movilepaytest.data.repositories.StatementRepository
import com.fernando.movilepaytest.feature.statement.model.Balance
import com.fernando.movilepaytest.feature.statement.model.Statement
import com.fernando.movilepaytest.feature.statement.model.Transactions
import com.fernando.movilepaytest.feature.statement.service.StatementService
import com.fernando.movilepaytest.feature.statement.view.StatementState
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response


@ExperimentalCoroutinesApi
class StatementTest {

    @MockK
    lateinit var statementRepository: StatementRepository

    @MockK
    lateinit var statementService: StatementService

    @MockK
    lateinit var statement: Statement

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        statementRepository = StatementRepository(statementService)
        every { statement.transactions } returns  mockk{
            arrayListOf<Transactions>(Transactions("", "", ""))
        }
        every { statement.balance } returns mockk{
            Balance("", "")
        }
    }

    @Test
    fun `should return StatementState after receive Response`() = runBlocking<Unit> {
        coEvery { statementRepository.getResponseStatement("123") } returns StatementState.StatementSuccess(statement)
        coEvery { statementService.requestStatement("123") } returns Response.success(statement)
        val result = statementRepository.getResponseStatement("123")
        Assert.assertTrue(result is StatementState.StatementSuccess)
    }
}