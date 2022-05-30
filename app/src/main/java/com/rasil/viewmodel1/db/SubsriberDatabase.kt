package com.rasil.viewmodel1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class SubsriberDatabase : RoomDatabase() {
    abstract val subsriberDAO: SubsriberDAO

    companion object {
        @Volatile
        private var INSTANCE: SubsriberDatabase? = null

        fun getInstance(context: Context): SubsriberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubsriberDatabase::class.java,
                        "subsriber_data_databas"
                    ).build()
                }
                return instance
            }
        }
    }
}