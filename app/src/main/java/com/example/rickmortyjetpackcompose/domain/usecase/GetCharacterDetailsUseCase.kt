package com.example.rickmortyjetpackcompose.domain.usecase

import com.example.rickmortyjetpackcompose.data.model.CharacterModel
import com.example.rickmortyjetpackcompose.domain.repository.CharacterRepository


class GetCharacterDetailsUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(id: Int): CharacterModel {
        return repository.getCharacterDetails(id)
    }
}