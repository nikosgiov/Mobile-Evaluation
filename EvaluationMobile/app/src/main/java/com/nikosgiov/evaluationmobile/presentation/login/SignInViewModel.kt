package com.nikosgiov.evaluationmobile.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikosgiov.evaluationmobile.domain.login.SignInUseCase
import com.nikosgiov.evaluationmobile.domain.login.UserIDUseCase
import com.nikosgiov.evaluationmobile.domain.model.LoginResult
import com.nikosgiov.evaluation.domain.use_case.login.PasswordUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val userIDUseCase: UserIDUseCase,
    private val passwordUseCase: PasswordUseCase
) : ViewModel() {
    val userIdSuccess = mutableStateOf(true)
    val passwordSuccess = mutableStateOf(true)

    private val _signInResult = MutableLiveData<LoginResult>()
    val signInResult: LiveData<LoginResult> = _signInResult

    fun signIn(userId: String, password: String) {
        userIdSuccess.value = userIDUseCase.execute(userId).successful
        passwordSuccess.value = passwordUseCase.execute(password).successful
        if (userIdSuccess.value && passwordSuccess.value) {
            viewModelScope.launch {
                try {
                    _signInResult.value = LoginResult.Loading
                    val result = signInUseCase.signIn(userId, password)
                    _signInResult.value = result
                } catch (e: IOException) {
                    _signInResult.value = LoginResult.Error("Network error occurred")
                } catch (e: HttpException) {
                    if (e.code() == 401) {
                        _signInResult.value = LoginResult.Error("Έχετε υποβάλει λανθασμένα στοιχεία")
                    } else {
                        _signInResult.value = LoginResult.Error("API error occurred")
                    }
                } catch (e: Exception) {
                    _signInResult.value = LoginResult.Error("Login failed")
                }
            }
        } else {
            _signInResult.value = LoginResult.Error("Έχετε υποβάλει μη αποδεκτά στοιχεία")
        }
    }
}
