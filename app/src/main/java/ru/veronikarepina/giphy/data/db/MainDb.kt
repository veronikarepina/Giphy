package ru.veronikarepina.giphy.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.veronikarepina.giphy.data.models.Data

@Database(entities = [Data::class], version = 1, exportSchema = true)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): GiphyDao

    companion object {
        @Volatile
        private var database: MainDb? = null

        @Synchronized
        fun getDb(context: Context): MainDb {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    MainDb::class.java,
                    "favorite_giphy.db"
                ).fallbackToDestructiveMigration().build()
                database as MainDb
            } else {
                database as MainDb
            }
        }
    }
}