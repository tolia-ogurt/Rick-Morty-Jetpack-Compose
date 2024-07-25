package com.example.rickmortyjetpackcompose.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyjetpackcompose.data.model.CharacterModel
import com.example.rickmortyjetpackcompose.domain.usecase.GetCharacterDetailsUseCase
import com.example.rickmortyjetpackcompose.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>> get() = _characters

    private val _selectedCharacter = MutableLiveData<CharacterModel?>()
    val selectedCharacter: LiveData<CharacterModel?> get() = _selectedCharacter

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage
    private var currentPage = 1
    private var isLastPage = false

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (_isLoading.value == true || isLastPage) return

        _isLoading.value = true
        viewModelScope.launch {
            try {
                val newCharacters = getCharactersUseCase(currentPage)
                if (newCharacters.isEmpty()) {
                    isLastPage = true
                } else {
                    val updatedCharacters = _characters.value.orEmpty() + newCharacters
                    _characters.value = updatedCharacters
                    currentPage++
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadCharacterDetails(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _selectedCharacter.value = getCharacterDetailsUseCase(id)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}