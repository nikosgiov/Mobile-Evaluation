package com.nikosgiov.evaluationmobile.presentation.magazines.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nikosgiov.evaluationmobile.R
import com.nikosgiov.evaluationmobile.ui.theme.ForestGreen

@Composable
fun BottomNavigationBar() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.tabs_bg),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.tabs_wave),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier.size(30.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_book),
                    contentDescription = "Book",
                    colorFilter = ColorFilter.tint(ForestGreen)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.size(30.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_misc),
                    contentDescription = "Misc",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.size(90.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.btn_play),
                    contentDescription = "Play"
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.size(30.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_link),
                    contentDescription = "Link",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.size(30.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = "Settings",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }
        }
    }
}