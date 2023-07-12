package com.nikosgiov.evaluationmobile.domain.model

sealed class LoginResult {
    object Loading : LoginResult()
    data class Success(val token: String, val tokenType: String) : LoginResult()
    data class Error(val errorMessage: String) : LoginResult()
}