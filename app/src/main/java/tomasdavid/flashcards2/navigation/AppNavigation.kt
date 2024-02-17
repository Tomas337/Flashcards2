package tomasdavid.flashcards2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tomasdavid.flashcards2.ui.screens.cardsscreen.CardsScreen
import tomasdavid.flashcards2.ui.screens.editscreen.EditScreen
import tomasdavid.flashcards2.ui.screens.mainscreen.MainScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Main.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Main.route) {
            MainScreen(navController)
        }
        composable(Screen.Edit.route) {navBackStackEntry ->
            val setId = navBackStackEntry.arguments?.getString("setId")?.toInt()
            setId?.let { setId ->
                EditScreen(navController, setId)
            }
        }
        composable(Screen.Cards.route) {navBackStackEntry ->
            val setId = navBackStackEntry.arguments?.getString("setId")?.toInt()
            setId?.let { setId ->
                CardsScreen(navController, setId)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Edit : Screen("edit/{setId}") {
        fun createRoute(setId: Int) = "edit/$setId"
    }
    object Cards: Screen("cards/{setId}") {
        fun createRoute(setId: Int) = "edit/$setId"
    }
}
