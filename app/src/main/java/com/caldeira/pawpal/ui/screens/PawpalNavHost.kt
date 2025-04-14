package com.caldeira.pawpal.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    data object CatBreedsScreen : Screen("mainCatScreen")
    data object FavoriteCatsScreen : Screen("favoriteCatScreen")
    data object CatDetailsScreen : Screen("favoriteCatScreen/{catId}") {
        fun createRoute(catId: String): String = "favoriteCatScreen/$catId"
        const val ID_ARG = "catId"
    }
}

@Composable
fun PawpalNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.CatBreedsScreen.route) {
        composable(Screen.CatBreedsScreen.route) { CatBreedsScreen(navController) }
        composable(Screen.FavoriteCatsScreen.route) { FavoriteCatsScreen(navController) }
        composable(
            route = Screen.CatDetailsScreen.route,
            arguments = listOf(navArgument(Screen.CatDetailsScreen.ID_ARG) { type = NavType.StringType })
        ) { _ ->
            CatDetailsScreen()
        }
    }
}
