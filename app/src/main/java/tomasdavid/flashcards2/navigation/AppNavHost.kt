package tomasdavid.flashcards2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import tomasdavid.flashcards2.screens.CardsScreen
import tomasdavid.flashcards2.screens.EditScreen
import tomasdavid.flashcards2.screens.MainScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) {
            MainScreen(navController)
        }
        composable(NavigationItem.Edit.route) {
            EditScreen(navController)
        }
        composable(NavigationItem.Cards.route) {
            CardsScreen(navController)
        }
    }
}