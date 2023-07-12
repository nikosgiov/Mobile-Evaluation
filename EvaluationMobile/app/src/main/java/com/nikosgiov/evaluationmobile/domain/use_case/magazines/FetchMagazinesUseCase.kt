package com.nikosgiov.evaluationmobile.domain.usecase

import ApiServiceRepository
import com.nikosgiov.evaluationmobile.common.Resource
import com.nikosgiov.evaluationmobile.domain.model.Magazine

class FetchMagazinesUseCase(private val apiServiceRepository: ApiServiceRepository) {
    suspend fun fetchMagazines(accessToken: String, tokenType: String): Resource<List<Magazine>> {
        return apiServiceRepository.getMagazines(accessToken, tokenType)
    }
}