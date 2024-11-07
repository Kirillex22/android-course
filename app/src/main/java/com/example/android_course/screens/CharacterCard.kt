package com.example.android_course.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.android_course.R
import com.example.android_course.model.Character

@Composable
fun DrawCharacterCard(
    navController: NavHostController,
    modifier: Modifier,
    character: Character
) {
    val scrollState = rememberScrollState()
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(character.fullImageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Superhero",
        contentScale = ContentScale.Crop,
        clipToBounds = true,
        modifier = modifier
            .fillMaxSize()
    )

    Column(
        modifier = modifier
            .padding(bottom = 30.dp)
            .verticalScroll(
                state = scrollState,
                enabled = true
            )
    ){
        Text(
            text = character.name,
            color = Color.White,
            fontSize = 35.sp,
            fontWeight = FontWeight(700),
            style = TextStyle(shadow = Shadow(Color.Black, blurRadius = 10.0f)),
            modifier = modifier
                .padding(start = 15.dp, top = 700.dp, bottom = 10.dp)
        )

        Text(
            style = TextStyle(shadow = Shadow(Color.Black, blurRadius = 10.0f)),
            text = character.description,
            fontSize = 25.sp,
            color = Color.White,
            modifier = modifier
                .padding(start = 15.dp, bottom = 50.dp)
        )
    }

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
                .background(Color.Black)
        )
    }
}