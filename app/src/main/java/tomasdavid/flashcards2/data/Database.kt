package tomasdavid.flashcards2.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [SetEntity::class], version = 1)
abstract class SetDatabase : RoomDatabase() {
    abstract fun setDao(): SetDao
}

@HiltAndroidApp
class MyApp : Application()

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        SetDatabase::class.java,
        "set.db"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: SetDatabase) = db.setDao()
}