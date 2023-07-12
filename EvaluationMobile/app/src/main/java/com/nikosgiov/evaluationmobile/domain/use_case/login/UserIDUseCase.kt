package com.nikosgiov.evaluationmobile.domain.login

import com.nikosgiov.evaluationmobile.domain.model.ValidationResult

class UserIDUseCase {

    fun validateUserID(userID: String): Boolean {
        val regex = Regex("^[A-Z]{2}\\d{4}$")
        return regex.matches(userID)
    }

    fun execute(userID: String): ValidationResult {
        if(userID.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "UserID can't be blank"
            )
        }
        if(!validateUserID(userID)){
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid UserID format"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}