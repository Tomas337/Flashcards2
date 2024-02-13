package tomasdavid.flashcards2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sets")
data class SetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val setName: String,
    val setImg: String = "",
    val cards: String = "",
    val displayOrder: String = ""
)