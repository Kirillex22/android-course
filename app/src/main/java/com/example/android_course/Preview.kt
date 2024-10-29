package com.example.android_course

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun DrawPreview(
    characters: List<Character>,
    navController: NavHostController,
    listState: LazyListState,
    modifier: Modifier
) {

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

        Row(
            modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 60.dp)
        ) {
            Text(text = "Choose your hero", color = Color.White, fontSize = 28.sp)
        }

        Box {
            val visibleItems = remember { mutableStateListOf<Character>() }
            LazyRow(
                state = listState,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = listState),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = modifier
                    .padding(top = 120.dp, bottom = 30.dp, start = 30.dp, end = 30.dp)
                    .fillMaxSize()
                    .scale(1.1f)
            ) {
                items(characters) { character ->
                    DrawCharacterPreview(
                        character,
                        navController,
                        modifier,
                        visibleItems
                    )
                }
            }
        }
    }
}



