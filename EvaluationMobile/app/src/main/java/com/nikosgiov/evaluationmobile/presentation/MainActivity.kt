package com.nikosgiov.evaluationmobile.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.nikosgiov.evaluationmobile.domain.model.LoginResult
import com.nikosgiov.evaluationmobile.presentation.common.TopAppBarSample
import com.nikosgiov.evaluationmobile.presentation.login.PasswordViewModel
import com.nikosgiov.evaluationmobile.presentation.login.SignInViewModel
import com.nikosgiov.evaluationmobile.presentation.login.UserIDViewModel
import com.nikosgiov.evaluationmobile.presentation.login.components.LanguageSelector
import com.nikosgiov.evaluationmobile.presentation.login.components.LoginAlertDialog
import com.nikosgiov.evaluationmobile.presentation.login.components.PasswordInput
import com.nikosgiov.evaluationmobile.presentation.login.components.UserIdInput
import com.nikosgiov.evaluationmobile.ui.theme.DollarBill
import com.nikosgiov.evaluationmobile.ui.theme.Green
import com.nikosgiov.evaluationmobile.ui.theme.EvaluationMobileTheme
import com.nikosgiov.evaluationmobile.utils.SharedPreferencesUtils.saveTokenInSharedPreferences
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvaluationMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Login()
                }
            }
        }
    }

    @Preview
    @Composable
    fun Login() {
        val userIDViewModel = getViewModel<UserIDViewModel>()
        val passwordViewModel = getViewModel<PasswordViewModel>()
        val signInViewModel = getViewModel<SignInViewModel>()
        val signInResult by signInViewModel.signInResult.observeAsState()

        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF15181C),
                            Color(0xFF000D05)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBarSample(title = "Σύνδεση")
            Column {
                Box(modifier = Modifier.padding(20.dp)) {
                    UserIdInput(userIDViewModel)
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Box(modifier = Modifier.padding(20.dp)) {
                    PasswordInput(passwordViewModel)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .align(Alignment.End)
                    .padding(20.dp)
            ) {
                LanguageSelector()
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    signInViewModel.signIn(userIDViewModel.userIdValue.value, passwordViewModel.passwordValue.value)
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(bottom = 30.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(24.5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = DollarBill
                ),
                border = BorderStroke(2.dp, Green)
            ) {
                Text("SIGN IN")
            }
            signInResult?.let { result ->
                when (result) {
                    is LoginResult.Loading -> {
                        CircularProgressIndicator(color = DollarBill)
                    }
                    is LoginResult.Success -> {
                        saveTokenInSharedPreferences(this@MainActivity, result.token, result.tokenType)
                        val intent = Intent(this@MainActivity, MainActivity2::class.java)
                        startActivity(intent)
                    }
                    is LoginResult.Error -> {
                        val alertDialogVisible = remember { mutableStateOf(true) }
                        LoginAlertDialog(showDialog = alertDialogVisible, description = result.errorMessage)
                    }
                }
            }
        }
    }
}






















