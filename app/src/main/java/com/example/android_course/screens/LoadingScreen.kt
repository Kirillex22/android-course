package com.example.android_course.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android_course.MARVEL_LOGO_LINK

@Composable
fun LoadingScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Black,
                        Color.DarkGray,
                        Color.Red
                    ), start = Offset(666.0f, 666.0f)
                )
            )
            .fillMaxSize()
    ) {
        Row(
            modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        ) {
            AsyncImage(
                model = MARVEL_LOGO_LINK,
                contentDescription = "Marvel Studios logo",
                modifier = modifier
                    .height(27.dp)
                    .width(128.dp)
            )
        }

        Column(
            modifier = modifier
                .padding(top = 150.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Text(text = "Loading...", color = Color.White, fontSize = 28.sp)
        }
    }
}