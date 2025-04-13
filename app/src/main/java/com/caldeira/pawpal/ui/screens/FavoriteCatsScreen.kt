package com.caldeira.pawpal.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.caldeira.pawpal.R
import com.caldeira.pawpal.ui.composables.CatsGrid
import com.caldeira.pawpal.ui.composables.TopBar
import com.caldeira.pawpal.ui.viewmodels.FavoriteCatsViewModel

@Composable
fun FavoriteCatsScreen(
    navController: NavHostController,
    viewmodel: FavoriteCatsViewModel = hiltViewModel()
) {
    val catsState = viewmodel.favoriteCatsListState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopBar(text = stringResource(R.string.favorites)) },
        modifier = Modifier.fillMaxSize()
    ) { innerPaddings ->
        CatsGrid(
            Modifier
                .fillMaxSize()
                .padding(innerPaddings),
            catsState.value
        ) { id, isFavorite ->
            viewmodel.setBreedIsFavorite(id, isFavorite)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteCatsScreenPreview() {
    val navController = rememberNavController()
    FavoriteCatsScreen(navController)
}