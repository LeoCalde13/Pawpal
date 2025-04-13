package com.caldeira.pawpal.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.caldeira.pawpal.R
import com.caldeira.pawpal.ui.composables.AnimatedGradientButton
import com.caldeira.pawpal.ui.composables.CatsGrid
import com.caldeira.pawpal.ui.composables.SearchBox
import com.caldeira.pawpal.ui.composables.TopBar
import com.caldeira.pawpal.ui.viewmodels.CatsBreedsViewModel

@Composable
fun CatBreedsScreen(navController: NavHostController, viewmodel: CatsBreedsViewModel = hiltViewModel()) {
    val catsState = viewmodel.breedsListState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewmodel.fetchCatBreeds()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = { TopBar(text = stringResource(R.string.app_name)) },
        modifier = Modifier.fillMaxSize()
    ) { innerPaddings ->
        Box(Modifier.fillMaxSize()) {
            CatsGrid(innerPaddings, catsState.value) { id, isFavorite->
                viewmodel.setBreedIsFavorite(id, isFavorite)
            }

            // FavoriteButton
            AnimatedGradientButton(
                Modifier
                    .padding(30.dp)
                    .align(Alignment.TopEnd),
                stringResource(R.string.favorites),
                onClick = {
                    navController.navigate(Screen.FavoriteCatsScreen.route)
                }
            )

            SearchBox(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(30.dp),
                viewmodel.searchState.value,
            ) { query ->
                viewmodel.searchBreed(query)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CatBreedsScreenPreview() {
    val controller = rememberNavController()
    CatBreedsScreen(controller)
}