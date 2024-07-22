package com.example.rickmortyjetpackcompose.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.rickmortyjetpackcompose.presentation.viewmodel.CharacterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailScreen(characterId: Int, viewModel: CharacterViewModel = koinViewModel()) {
    val character by viewModel.selectedCharacter.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()

    LaunchedEffect(characterId) {
        viewModel.loadCharacterDetails(characterId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            character?.let {
                Image(
                    painter = rememberImagePainter(data = it.image),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.name, style = MaterialTheme.typography.titleMedium)
                Text(text = it.status, style = MaterialTheme.typography.bodyMedium)
                Text(text = it.species, style = MaterialTheme.typography.bodyMedium)
            }
        }

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}