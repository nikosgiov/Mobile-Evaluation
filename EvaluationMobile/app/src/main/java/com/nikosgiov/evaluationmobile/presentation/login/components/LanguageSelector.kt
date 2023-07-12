package com.nikosgiov.evaluationmobile.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
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
import com.nikosgiov.evaluationmobile.ui.theme.Onyx

@Composable
fun LanguageSelector() {
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("Greek") }

    Column(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
                .background(
                    color = Onyx,
                    shape = RoundedCornerShape(25.5.dp)
                )
                .clickable { expanded = !expanded }
                .padding(horizontal = 16.dp)
                .height(56.dp)
        ) {
            Row(modifier = Modifier.align(Alignment.Center)) {
                Image(
                    painter = painterResource(
                        when (selectedLanguage) {
                            "Greek" -> R.drawable.greece_flag_round_icon
                            "English" -> R.drawable.united_states_of_america_flag_round
                            else -> R.drawable.greece_flag_round_icon
                        }
                    ),
                    contentDescription = "Flag",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = selectedLanguage,
                    color = Color.White,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Icon(
                    painter = painterResource(R.drawable.arrow_down),
                    contentDescription = "Dropdown Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(25.dp))) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Onyx)
            ) {
                DropdownMenuItem(onClick = {
                    selectedLanguage = "English"
                    expanded = false
                }) {
                    Row(Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(R.drawable.united_states_of_america_flag_round),
                            contentDescription = "USA Flag",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "English", color = Color.White)
                    }
                }
                DropdownMenuItem(
                    onClick = {
                        selectedLanguage = "Greek"
                        expanded = false
                    }
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(R.drawable.greece_flag_round_icon),
                            contentDescription = "Greece Flag",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Greek", color = Color.White)
                    }
                }
            }
        }
    }
}