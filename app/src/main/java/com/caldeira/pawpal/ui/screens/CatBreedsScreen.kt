package com.caldeira.pawpal.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.caldeira.pawpal.R
import com.caldeira.pawpal.model.CatDetails
import com.caldeira.pawpal.ui.composables.AnimatedGradientButton
import com.caldeira.pawpal.ui.composables.CatCard
import com.caldeira.pawpal.ui.composables.SearchBox
import com.caldeira.pawpal.ui.viewmodels.MainViewModel


@Composable
fun CatBreedsScreen(viewmodel: MainViewModel = viewModel()) {
    val catsList = viewmodel.breedsListState.value
    Log.d("CatBreedsScreen", "cats=$catsList")
    Scaffold(
        topBar = { TopBar(text = stringResource(R.string.app_name)) },
        modifier = Modifier.fillMaxSize()
    ) { innerPaddings ->
        Box(Modifier.fillMaxSize()) {
            CatsGrid(innerPaddings, catsList)
            AnimatedGradientButton(
                Modifier
                    .padding(30.dp)
                    .align(Alignment.TopEnd),
                stringResource(R.string.favorites)
            )

            SearchBox(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(30.dp)
            )
        }
    }
}

@Composable
fun CatsGrid(paddingValues: PaddingValues, cats: List<CatDetails>) {
    val state = remember { LazyGridState() }

    LazyVerticalGrid(
        // todo review grid cells size
        columns = GridCells.FixedSize(150.dp),
        state = state,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(bottom = 60.dp)
    ) {
        items(cats.size) {
            cats[it].let { cat ->
                CatCard(PaddingValues(2.dp), cat.name, cat.isFavorite, cat.lifeExpectancy)
            }
        }
    }
}

@Composable
fun TopBar(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    0.0f to Color.White,
                    0.5f to Color.White,
                    1f to Color(1, 1, 1, 0)
                ),
            )
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 10.dp),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = false)
@Composable
fun TopBarPreview() {
    TopBar(stringResource(R.string.app_name))
}

@Preview(showBackground = true)
@Composable
private fun CatBreedsScreenPreview() {
    CatBreedsScreen()
}