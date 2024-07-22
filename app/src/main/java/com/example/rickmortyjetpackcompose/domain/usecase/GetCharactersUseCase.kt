package com.example.rickmortyjetpackcompose.domain.usecase

import com.example.rickmortyjetpackcompose.data.model.CharacterModel
import com.example.rickmortyjetpackcompose.domain.repository.CharacterRepository

class GetCharactersUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(page: Int): List<CharacterModel> {
        return repository.getCharacters(page)
    }
}