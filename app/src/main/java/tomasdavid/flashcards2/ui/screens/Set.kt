package tomasdavid.flashcards2.ui.screens

data class Card(
    val text1: String,
    val text2: String,
    val cardImg: String = "",  // TODO add default img path
)

data class Set(
    val id: Int?,
    val setName: String,
    val setImg: String = "",  // TODO add default img path
    var cards: MutableList<Card> = mutableListOf<Card>(),
    val displayOrder: List<List<String>> = listOf(listOf("text1", "cardImg"), listOf("text2"), listOf())
)