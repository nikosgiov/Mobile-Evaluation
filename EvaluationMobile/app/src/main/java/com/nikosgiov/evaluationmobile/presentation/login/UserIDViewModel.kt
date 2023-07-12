package com.nikosgiov.evaluationmobile.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nikosgiov.evaluationmobile.domain.login.UserIDUseCase
import androidx.compose.runtime.State

class UserIDViewModel(
    private val userIDUseCase: UserIDUseCase
) : ViewModel() {
    private val _userIdValue = mutableStateOf("")
    private val _success = mutableStateOf(true)

    val userIdValue: State<String> = _userIdValue
    val success: State<Boolean> = _success

    fun onValueChange(newValue: String) {
        _userIdValue.value = newValue
        _success.value = userIDUseCase.execute(newValue).successful
    }
}
