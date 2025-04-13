package com.caldeira.pawpal.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    data object CatBreedsScreen : Screen("mainCatScreen")
    data object FavoriteCatsScreen : Screen("favoriteCatScreen")
    data object CatDetailsScreen : Screen("favoriteCatScreen/{catId}") {
        fun createRoute(catId: String): String = "favoriteCatScreen/$catId"
    }
}

@Composable
fun PawpalNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.CatBreedsScreen.route) {
        composable(Screen.CatBreedsScreen.route) { CatBreedsScreen(navController) }
        composable(Screen.FavoriteCatsScreen.route) { FavoriteCatsScreen(navController) }
/*        composable(
            route = Screen.CatDetailsScreen.route,
            arguments = listOf(navArgument("catId") { type = NavType.StringType })
        ) { backStackEntry ->
            val catId = backStackEntry.arguments?.getString("catId")
            CatDetailsScreen(navController, catId)
        }*/
    }
}
