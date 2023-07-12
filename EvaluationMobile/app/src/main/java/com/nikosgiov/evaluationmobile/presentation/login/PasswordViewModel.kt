package com.nikosgiov.evaluationmobile.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nikosgiov.evaluation.domain.use_case.login.PasswordUseCase
import androidx.compose.runtime.State

class PasswordViewModel(
    private val passwordUseCase: PasswordUseCase
) : ViewModel() {
    private val _passwordValue = mutableStateOf("")
    private val _success = mutableStateOf(true)

    val passwordValue: State<String> = _passwordValue
    val success: State<Boolean> = _success

    fun onValueChange(newValue: String) {
        _passwordValue.value = newValue
        _success.value = passwordUseCase.execute(newValue).successful
    }
}
