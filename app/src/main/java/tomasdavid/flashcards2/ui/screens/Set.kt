package tomasdavid.flashcards2.ui.screens

data class Card(
    var id: Int = -1,
    var text1: String = "",
    var text2: String = "",
    var cardImg: String = "file:///android_asset/no-image.1024x1024.png",
)

data class Set(
    val id: Int? = null,
    var setName: String,
    var setImg: String = "file:///android_asset/no-image.1024x1024.png",
    var cards: MutableList<Card> = mutableListOf(),
    var displayOrder: List<List<String>> = listOf(listOf("text1", "cardImg"), listOf("text2"), listOf())
)