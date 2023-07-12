package com.nikosgiov.evaluationmobile.presentation.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nikosgiov.evaluationmobile.R
import com.nikosgiov.evaluationmobile.presentation.login.PasswordViewModel
import com.nikosgiov.evaluationmobile.presentation.login.UserIDViewModel
import com.nikosgiov.evaluationmobile.ui.theme.ForestGreen

@Composable
fun UserIdInput(viewModel: UserIDViewModel) {
    var value: String by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row {
                Text(
                    text = "UserID",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 10.dp),
                    color = Color.White
                )
                IconButton(
                    onClick = {
                        showDialog = !showDialog
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        Row {
            InputBox(
                value = value,
                onValueChange = { newValue ->
                    value = newValue
                    viewModel.onValueChange(newValue)
                },
                isValid = viewModel.success.value,
                passwordVisibility = true
            )
        }
        Column() {
            if (showDialog) {
                InfoDialog(
                    message = "Τουλάχιστον 8 χαρακτήρες (2 κεφαλαία, 3 πεζά, 1 ειδικός χαρακτήρας, 2 νούμερα)",
                    onDismissRequest = { showDialog = false }
                )
            }
        }
    }
}

@Composable
fun PasswordInput(viewModel: PasswordViewModel) {
    var value: String by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                Text(
                    text = "Κωδικός",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 10.dp),
                    color = Color.White
                )
                IconButton(
                    onClick = {
                        showDialog = !showDialog
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Row() {
                Box(
                    modifier = Modifier
                        .clickable { passwordVisibility = !passwordVisibility }
                        .width(IntrinsicSize.Max)
                ) {
                    Text(
                        text = if (passwordVisibility) "Απόκρυψη" else "Προβολή",
                        style = MaterialTheme.typography.body1,
                        color = ForestGreen
                    )
                }
            }
        }
        Row {
            InputBox(
                value = value,
                onValueChange = { newValue ->
                    value = newValue
                    viewModel.onValueChange(newValue)
                },
                isValid = viewModel.success.value,
                passwordVisibility = passwordVisibility
            )
        }
        Row() {
            if (showDialog) {
                InfoDialog(
                    message = "Ακριβώς 6 χαρακτήρες ( 2 κεφαλαία γράμματα και έπειτα 4 νούμερα)",
                    onDismissRequest = { showDialog = false }
                )
            }
        }
    }
}