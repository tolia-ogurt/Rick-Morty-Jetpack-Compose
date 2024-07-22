package com.example.rickmortyjetpackcompose.data.repository

import com.example.rickmortyjetpackcompose.data.model.CharacterModel
import com.example.rickmortyjetpackcompose.data.remote.RickAndMortyApi
import com.example.rickmortyjetpackcompose.domain.repository.CharacterRepository

class CharacterRepositoryImpl(private val api: RickAndMortyApi) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<CharacterModel> {
        return api.getCharacters(page).results
    }

    override suspend fun getCharacterDetails(id: Int): CharacterModel {
        return api.getCharacterDetails(id)
    }
}