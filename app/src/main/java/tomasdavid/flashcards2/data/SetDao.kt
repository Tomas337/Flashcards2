package tomasdavid.flashcards2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SetDao {
    @Query("SELECT * FROM sets")
    fun getAllSets(): List<SetEntity>

    @Query("SELECT * FROM sets WHERE id == :id")
    fun getSet(id: Int): SetEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertSet(setEntity: SetEntity): Long

    @Query("DELETE FROM sets WHERE id == :id")
    fun deleteSet(id: Int)
}