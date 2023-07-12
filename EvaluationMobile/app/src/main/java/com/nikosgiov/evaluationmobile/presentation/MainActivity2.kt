package com.nikosgiov.evaluationmobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.nikosgiov.evaluationmobile.common.Resource
import com.nikosgiov.evaluationmobile.presentation.common.TopAppBarSample
import com.nikosgiov.evaluationmobile.presentation.magazines.MagazineViewModel
import com.nikosgiov.evaluationmobile.presentation.magazines.components.BottomNavigationBar
import com.nikosgiov.evaluationmobile.presentation.magazines.components.MagazineRow
import com.nikosgiov.evaluationmobile.ui.theme.DollarBill
import com.nikosgiov.evaluationmobile.ui.theme.EvaluationMobileTheme
import com.nikosgiov.evaluationmobile.utils.SharedPreferencesUtils.retrieveTokenFromSharedPreferences
import com.nikosgiov.evaluationmobile.utils.SharedPreferencesUtils.retrieveTokenTypeFromSharedPreferences
import org.koin.androidx.compose.getViewModel

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvaluationMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MagazineActivity()
                }
            }
        }
    }

    @Preview
    @Composable
    fun MagazineActivity() {
        val viewModel = getViewModel<MagazineViewModel>()
        val yearsState = remember { mutableStateOf(emptyList<String>()) }
        val magazinesState = viewModel.magazineListLiveData.observeAsState(Resource.Loading())

        viewModel.fetchMagazines(retrieveTokenFromSharedPreferences(this), retrieveTokenTypeFromSharedPreferences(this))

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBarSample(title = "Περιοδικά")
            },
            bottomBar = {
                Box(Modifier.height(120.dp)) {
                    BottomNavigationBar()
                }
            },
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF15181C), Color(0xFF000D05)),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                ) {
                    when (val magazinesResult = magazinesState.value) {
                        is Resource.Loading -> {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                                color = DollarBill)
                        }
                        is Resource.Success -> {
                            val magazines = magazinesResult.data
                            if (magazines != null) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    yearsState.value = magazines.map { magazine ->
                                        val dateParts = magazine.date_released.substringBefore("T").split("-")
                                        "${dateParts[0]}-${dateParts[1]}"
                                    }.distinct().sortedDescending()
                                    yearsState.value.forEach { yearMonth ->
                                        val magazinesByYearMonth = magazines.filter { magazine ->
                                            val dateParts = magazine.date_released.substringBefore("T").split("-")
                                            "${dateParts[0]}-${dateParts[1]}" == yearMonth
                                        }
                                        MagazineRow(date = yearMonth, magazines = magazinesByYearMonth)
                                    }
                                }
                            } else {
                                Text(
                                    text = "No magazines available",
                                    modifier = Modifier.padding(16.dp),
                                    color = Color.Red
                                )
                            }
                        }
                        is Resource.Error -> {
                            Text(
                                text = "Error: ${magazinesResult.message}",
                                modifier = Modifier.padding(16.dp),
                                color = Color.Red
                            )
                        }
                    }
                }
            }
        )
    }

}