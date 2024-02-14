package tomasdavid.flashcards2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sets")
data class SetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val setName: String,
    val setImg: String = "file:///android_asset/no-image.1024x1024.png",
    val cards: String = "",
    val displayOrder: String = ""
)