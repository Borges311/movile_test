package com.fernando.movilepaytest.feature.statement.view

import com.fernando.movilepaytest.feature.statement.model.Statement

sealed class StatementState{

    data class StatementSuccess(val data: Statement?): StatementState()
    data class StatementError(val error: String?): StatementState()
}
