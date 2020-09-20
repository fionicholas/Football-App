package com.fionicholas.footballapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fionicholas.footballapp.data.matchfavorite.local.response.MatchFavorite
import com.fionicholas.footballapp.data.teamfavorite.local.response.TeamFavorite

@Database(entities = [MatchFavorite::class, TeamFavorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "db-android")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun favoriteDao(): FavoriteDao

}