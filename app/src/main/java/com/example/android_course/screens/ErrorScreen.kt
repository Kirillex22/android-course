package com.example.android_course.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.android_course.R

@Composable
fun ErrorScreen(modifier: Modifier, navController: NavHostController) {
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
        Box(
            modifier = modifier
                .padding(start = 20.dp, top = 35.dp)
                .size(40.dp, 35.dp)
                .clickable(onClick = { navController.navigate("characters_preview") })
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.left_arrow),
                contentDescription = "Left arrow",
                modifier = modifier
                    .fillMaxSize()
            )
        }
            Column(
                modifier = modifier
                    .padding(top = 200.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_connection_error),
                    contentDescription = ""
                )
                Text(
                    text = "Возникла ошибка при работе с сетью",
                    modifier = modifier.padding(top = 16.dp, start = 30.dp, end = 30.dp),
                    color = Color.White,
                    fontSize = 28.sp,
                )
           }
    }
}