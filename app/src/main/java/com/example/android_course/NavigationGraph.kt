package com.example.android_course

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(navController: NavHostController) {
    val listState = rememberLazyListState()

    NavHost(
        navController = navController,
        startDestination = "characters_preview"
    ) {

        composable("characters_preview") {
            DrawPreview(CHARACTERS, navController, listState, Modifier)
        }

        composable(
            route = "{index}_info",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            DrawCharacterCard(
                character = CHARACTERS[backStackEntry.arguments?.getInt("index")!!],
                navController = navController,
                modifier = Modifier
            )
        }
    }
}