package com.example.android_course

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_course.model.Character
import com.example.android_course.network.MarvelApi
import kotlinx.coroutines.launch



sealed interface CharacterListUiState {
    data class Success(val characters: List<Character>) : CharacterListUiState
    data object Loading: CharacterListUiState
    data object Error: CharacterListUiState
}

sealed interface CharacterUiState{
    data class Success(val character: Character): CharacterUiState
    data object Loading: CharacterUiState
    data object Error: CharacterUiState
}

class CharacterListViewModel(): ViewModel(){
    var characterListUiState: CharacterListUiState by mutableStateOf(CharacterListUiState.Loading)
        private set

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadCharacterList() {
        viewModelScope.launch {
            characterListUiState = try {
                val listResult = MarvelApi.retrofitService.getCharacters().data.results
                CharacterListUiState.Success(listResult.filter { it.fullImageUrl != EMPTY_IMAGE_URL })
            } catch (e: Exception) {
                println("EXCEPTION ${e.message}")
                CharacterListUiState.Error
            }
        }
    }
}

class CharacterViewModel(): ViewModel(){
    var characterUiState: CharacterUiState by mutableStateOf(CharacterUiState.Loading)
        private set

    fun loadCharacter(id: Int){
        viewModelScope.launch {
            characterUiState = try {
                characterUiState = CharacterUiState.Loading
                val result = MarvelApi.retrofitService.getCharacter(id).data.results.first()
                CharacterUiState.Success(result)
            } catch (e: Exception) {
                println("EXCEPTION ${e.message}")
                CharacterUiState.Error
            }
        }
    }
}


