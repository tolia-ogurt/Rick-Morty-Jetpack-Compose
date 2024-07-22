package com.example.rickmortyjetpackcompose.data.remote

import com.example.rickmortyjetpackcompose.data.model.CharacterModel
import com.example.rickmortyjetpackcompose.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): CharacterModel
}