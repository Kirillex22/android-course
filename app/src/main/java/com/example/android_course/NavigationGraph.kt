package com.example.android_course

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.android_course.screens.DrawCharacterCard
import com.example.android_course.screens.DrawPreview
import com.example.android_course.screens.ErrorScreen
import com.example.android_course.screens.LoadingScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    val listState = rememberLazyListState()
    val chListViewModel = CharacterListViewModel()
    val chViewModel = CharacterViewModel()
    chListViewModel.loadCharacterList()

    NavHost(
        navController = navController,
        startDestination = "characters_preview"
    ) {
        composable("characters_preview") {
            when(val chListUiState = chListViewModel.characterListUiState){
                is CharacterListUiState.Success ->
                    DrawPreview(
                        navController,
                        listState,
                            Modifier,
                        chListUiState.characters,
                        chViewModel
                    )
                is CharacterListUiState.Loading -> LoadingScreen(modifier = Modifier)
                is CharacterListUiState.Error -> ErrorScreen(modifier = Modifier, navController)
            }
        }

        composable(
            route = "{id}_info",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED){
                when(val chUiState = chViewModel.characterUiState){
                    is CharacterUiState.Success ->
                        DrawCharacterCard(
                            navController = navController,
                            modifier = Modifier,
                            chUiState.character
                        )
                    is CharacterUiState.Loading -> LoadingScreen(modifier = Modifier)
                    is CharacterUiState.Error -> ErrorScreen(modifier = Modifier, navController)
                }
            }
        }
    }
}