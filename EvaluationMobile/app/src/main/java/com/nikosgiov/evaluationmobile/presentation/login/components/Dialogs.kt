package com.nikosgiov.evaluationmobile.presentation.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nikosgiov.evaluationmobile.ui.theme.Onyx
import com.nikosgiov.evaluationmobile.ui.theme.UFOGreen

@Composable
fun InfoDialog(
    message: String,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        backgroundColor = Onyx,
        contentColor = Color.White,
        shape = RoundedCornerShape(12.dp),
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.body1
            )
        },
        buttons = {}
    )
}

@Composable
fun LoginAlertDialog(showDialog: MutableState<Boolean>, description: String) {
    if (showDialog.value) {
        AlertDialog(
            backgroundColor = Onyx,
            shape = RoundedCornerShape(14),
            onDismissRequest = {},
            buttons = {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Onyx),
                    onClick = { showDialog.value = false }
                ) {
                    Text(
                        text = "Επιστροφή",
                        color = UFOGreen
                    )
                }
            },
            title = {
                Text(
                    text = "Λανθασμένα Στοιχεία",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    text = description,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}