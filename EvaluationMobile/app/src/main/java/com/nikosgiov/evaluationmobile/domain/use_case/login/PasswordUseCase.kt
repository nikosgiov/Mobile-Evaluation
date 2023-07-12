package com.nikosgiov.evaluation.domain.use_case.login

import com.nikosgiov.evaluationmobile.domain.model.ValidationResult

class PasswordUseCase {

    fun validatePassword(password: String): Boolean {
        val regex = Regex("^(?=.*[A-Z].*[A-Z])(?=.*[a-z].*[a-z].*[a-z])(?=.*\\d.*\\d)(?=.*[^a-zA-Z\\d]).{8,}$")
        return regex.matches(password)
    }

    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must have at least 8 characters"
            )
        }
        if (!validatePassword(password)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid password format"
            )
        }
        return ValidationResult(successful = true)
    }

}
