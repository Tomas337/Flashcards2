package tomasdavid.flashcards2.ui.screens

data class Card(
    val id: Int = -1,
    val text1: String = "",
    val text2: String = "",
    val cardImg: String = "file:///android_asset/no-image.1024x1024.png",
)

data class Set(
    val id: Int? = null,
    val setName: String,
    val setImg: String = "file:///android_asset/no-image.1024x1024.png",
    var cards: MutableList<Card> = mutableListOf<Card>(),
    val displayOrder: List<List<String>> = listOf(listOf("text1", "cardImg"), listOf("text2"), listOf())
)