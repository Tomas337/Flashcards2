package tomasdavid.flashcards2.screens

class Card(
    val text1: String,
    val text2: String,
    val cardImg: String = "",  // TODO add default img path
)

class Set(
    val setName: String,
    val setImg: String = "",  // TODO add default img path
) {
    var cards = mutableListOf<Card>()
    val displayOrder = listOf(listOf("text1", "cardImg"), listOf("text2"), listOf())
}
