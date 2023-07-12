package com.nikosgiov.evaluationmobile.presentation.login.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.nikosgiov.evaluationmobile.ui.theme.ForestGreen
import com.nikosgiov.evaluationmobile.ui.theme.Lust

@Composable
fun InputBox(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    passwordVisibility: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            maxLines = 1,
            onValueChange = onValueChange,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = ForestGreen,
                unfocusedIndicatorColor = ForestGreen,
                backgroundColor = Color.Transparent,
                errorIndicatorColor = Lust,
                textColor = Color.White
            ),
            isError = !isValid
        )
        if (!isValid) {
            Text(
                text = "!",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

