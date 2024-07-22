package com.example.rickmortyjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickmortyjetpackcompose.presentation.ui.CharacterDetailScreen
import com.example.rickmortyjetpackcompose.presentation.ui.CharacterListScreen
import com.example.rickmortyjetpackcompose.presentation.viewmodel.CharacterViewModel
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyAp()
        }
    }

    @Composable
    fun RickAndMortyAp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "characters") {
            composable("characters") {
                CharacterListScreen(navController)
            }
            composable("characters/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId")?.toInt()
                characterId?.let {
                    CharacterDetailScreen(characterId)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        RickAndMortyAp()
    }
}