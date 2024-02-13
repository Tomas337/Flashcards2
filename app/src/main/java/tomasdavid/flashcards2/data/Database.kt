package tomasdavid.flashcards2.data

import android.app.Application
import androidx.room.Database
import androidx.room.RoomDatabase
import dagger.hilt.android.HiltAndroidApp

@Database(entities = [SetEntity::class], version = 1)
abstract class SetDatabase : RoomDatabase() {
    abstract fun setDao(): SetDao
}

@HiltAndroidApp
class MyApp : Application()