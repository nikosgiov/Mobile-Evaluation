package com.nikosgiov.evaluationmobile.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
