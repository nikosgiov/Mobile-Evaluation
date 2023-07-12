package com.nikosgiov.evaluationmobile.domain.login

import ApiServiceRepository
import com.nikosgiov.evaluationmobile.domain.model.LoginResult

class SignInUseCase(private val apiServiceRepository: ApiServiceRepository) {
    suspend fun signIn(userId: String, password: String): LoginResult {
        return apiServiceRepository.login(userId, password)
    }
}

