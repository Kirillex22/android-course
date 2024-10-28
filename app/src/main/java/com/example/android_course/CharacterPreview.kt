package com.example.android_course

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DrawCharacterPreview(
    character: Character,
    navController: NavHostController,
    modifier: Modifier
) {

    var isSelected by remember { mutableStateOf(value = false) }

    Column(
        modifier = modifier
            .clickable(
                onClick = {
                    navController.navigate(
                        route = "${
                            CHARACTERS.indexOf(character)
                        }_info"
                    )
                    isSelected = true
                }
            )
            .blur(radius = if (isSelected) 10.dp else 0.dp)
    ) {
        Box(){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.imageLink)
                    .crossfade(true)
                    .build(),
                contentDescription = "Superhero",
                contentScale = ContentScale.Crop,
                clipToBounds = true,
                modifier = Modifier
                    .size(300.dp, 550.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            )

            Text(
                text = character.name, color = Color.White, fontSize = 30.sp, modifier = modifier
                    .padding(start = 15.dp, top = 500.dp)
            )
        }
    }
}