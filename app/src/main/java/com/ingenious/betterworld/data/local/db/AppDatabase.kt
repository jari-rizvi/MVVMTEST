package com.ingenious.betterworld.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ingenious.betterworld.constants.AppConstants
import com.ingenious.betterworld.data.models.MusicModel


@Database(entities = [MusicModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun appDao(): AppDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance
                ?: synchronized(this) { instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, AppConstants.DbConfiguration.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}