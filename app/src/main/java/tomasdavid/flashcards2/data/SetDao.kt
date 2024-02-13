package tomasdavid.flashcards2.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SetDao {
    @Query("SELECT * FROM sets")
    fun getAllSets(): List<SetEntity>

    @Query("SELECT * FROM sets WHERE id == :id")
    fun getSet(id: Int): SetEntity

    @Upsert
    fun upsertSet(setEntity: SetEntity)

    @Query("DELETE FROM sets WHERE id == :id")
    fun deleteSet(id: Int)
}