package com.madman.moviesapp.data.resource.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity

@Database(
    entities = [MoviesEntity::class, TVShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesAppDatabase : RoomDatabase() {

    abstract fun moviesAppDao(): MoviesAppDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesAppDatabase? = null

        fun getInstance(context: Context): MoviesAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MoviesAppDatabase::class.java,
                    "movies_app.db"
                ).build()
            }
    }
}