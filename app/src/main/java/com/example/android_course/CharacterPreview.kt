package com.example.android_course

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
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
    modifier: Modifier,
    visibleItems: SnapshotStateList<Character>
) {

    var isVisible by remember { mutableStateOf(false)}
    var isSelected by remember { mutableStateOf(value = false) }
    val scale by animateFloatAsState(targetValue = if (character in visibleItems) 1f else 0.8f,
        label = "scaling"
    )

    Box(
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
            .onGloballyPositioned { layoutCoordinates ->
                val position = layoutCoordinates.positionInRoot()
                isVisible = position.x >= 0 && position.x < S_W

                if (isVisible && !visibleItems.contains(character)) {
                    visibleItems.add(character)
                } else if (!isVisible && visibleItems.contains(character)) {
                    visibleItems.remove(character)
                }
            }
            .size(300.dp, 550.dp)
            .scale(scale)
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.imageLink)
                .crossfade(true)
                .build(),
            contentDescription = "Superhero",
            contentScale = ContentScale.Crop,
            clipToBounds = true,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp))
        )

        Text(
            text = character.name, color = Color.White, fontSize = 35.sp, modifier = modifier
                .padding(start = 15.dp, top = 500.dp)
        )
    }
}
