package com.example.rickmortyjetpackcompose.domain.repository

import com.example.rickmortyjetpackcompose.data.model.CharacterModel

interface CharacterRepository {
    suspend fun getCharacters(page: Int): List<CharacterModel>
    suspend fun getCharacterDetails(id: Int): CharacterModel
}