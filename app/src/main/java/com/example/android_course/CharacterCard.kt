package com.example.android_course

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DrawCharacterCard(
    character: Character,
    navController: NavHostController,
    modifier: Modifier
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(character.imageLink)
            .crossfade(true)
            .build(),
        contentDescription = "Superhero",
        contentScale = ContentScale.Crop,
        clipToBounds = true,
        modifier = modifier
            .fillMaxSize()
    )

    Text(
        text = character.name, color = Color.White, fontSize = 30.sp, modifier = modifier
            .padding(start = 15.dp, top = 750.dp)
    )
    Text(
        text = character.welcomeMessage, color = Color.White, modifier = modifier
            .padding(start = 15.dp, top = 800.dp)
    )
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





}