package tomasdavid.flashcards2.navigation

enum class Screen {
    MAIN,
    EDIT,
    CARDS,
}
sealed class NavigationItem(val route: String) {
    object Main : NavigationItem(Screen.MAIN.name)
    object Edit : NavigationItem(Screen.EDIT.name)
    object Cards: NavigationItem(Screen.CARDS.name)
}