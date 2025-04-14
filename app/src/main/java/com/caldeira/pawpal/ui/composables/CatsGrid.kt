package com.caldeira.pawpal.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.caldeira.pawpal.model.CatDetails
import com.caldeira.pawpal.ui.screens.Screen


@Composable
fun CatsGrid(
    navController: NavHostController,
    modifier: Modifier,
    items: List<CatDetails>,
    showLifeSpan: Boolean,
    onFavoriteClicked: (String, Boolean) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.FixedSize(150.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
        modifier = modifier,
    ) {
        items(items.size) { i ->
            Log.d("CatsGrid", "${items[i]}")
            CatCard(
                innerPadding = PaddingValues(2.dp),
                catDetails = items[i],
                showLifeSpan = showLifeSpan,
                onCardClicked = { navController.navigate(Screen.CatDetailsScreen.createRoute(items[i].id)) },
                onFavoriteClicked = {
                    onFavoriteClicked(items[i].id, !items[i].isFavorite)
                },
            )
        }
    }
}