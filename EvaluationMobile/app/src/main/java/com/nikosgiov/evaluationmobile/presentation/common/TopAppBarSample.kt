package com.nikosgiov.evaluationmobile.presentation.common

import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nikosgiov.evaluationmobile.ui.theme.Onyx

@Composable
fun TopAppBarSample(title: String) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            modifier = Modifier.height(80.dp).fillMaxWidth(),
            title = {
                Text(
                    title,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            backgroundColor = Onyx,
        )
    }
}