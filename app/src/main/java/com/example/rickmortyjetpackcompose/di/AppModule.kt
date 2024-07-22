package com.example.rickmortyjetpackcompose.di

import com.example.rickmortyjetpackcompose.data.remote.RickAndMortyApi
import com.example.rickmortyjetpackcompose.data.repository.CharacterRepositoryImpl
import com.example.rickmortyjetpackcompose.domain.repository.CharacterRepository
import com.example.rickmortyjetpackcompose.domain.usecase.GetCharacterDetailsUseCase
import com.example.rickmortyjetpackcompose.domain.usecase.GetCharactersUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import com.example.rickmortyjetpackcompose.presentation.viewmodel.CharacterViewModel
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    single<CharacterRepository> { CharacterRepositoryImpl(get()) }

    single { GetCharactersUseCase(get()) }
    single { GetCharacterDetailsUseCase(get()) }

    viewModel { CharacterViewModel(get(), get()) }
}