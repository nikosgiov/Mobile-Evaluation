package com.nikosgiov.evaluationmobile.presentation.magazines.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nikosgiov.evaluationmobile.R
import com.nikosgiov.evaluationmobile.domain.model.Magazine
import com.nikosgiov.evaluationmobile.ui.theme.Green


@Composable
fun MagazineRow(date: String, magazines: List<Magazine>) {
    val isCheckVisibleList = remember { mutableStateListOf<Boolean>() }
    repeat(magazines.size) {
        isCheckVisibleList.add(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        Text(
            text = date,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 14.dp)
        )
        Row(
            Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            magazines.forEachIndexed { index, magazine ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(140.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(160.dp, 160.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        val isCheckVisible = isCheckVisibleList[index]
                        AsyncImage(
                            model = magazine.img_url,
                            contentDescription = "Magazine preview image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize().clickable(onClick = {
                                isCheckVisibleList[index] = !isCheckVisible
                            })
                        )
                        if (isCheckVisible) {
                            Marked(Modifier.size(60.dp).align(Alignment.BottomEnd))
                        }
                    }
                    Text(
                        text = magazine.title,
                        color = Color.White,
                        fontSize = 22.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun Marked(modifier: Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(
                path = Path().apply {
                    moveTo(size.width, size.height) // Start at the bottom-right corner
                    lineTo(0f, size.height) // Draw a horizontal line to the bottom-left corner
                    lineTo(size.width, 0f) // Draw a diagonal line to the top-right corner
                    close() // Close the path
                },
                color = Green
            )
        }
        Box(
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.BottomEnd)
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_check_w),
                contentDescription = "Check Icon",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


