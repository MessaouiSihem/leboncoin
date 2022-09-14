package com.leboncoin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leboncoin.data.dao.AlbumDao
import com.leboncoin.data.entity.AlbumEntity

@Database(
    entities = [AlbumEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LeBonCoinDataBase: RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object {

        var TEST_MODE = false
        const val DATABASE_NAME = "le_bon_coin_database.db"

        @Volatile
        private var INSTANCE: LeBonCoinDataBase? = null

        fun getInstance(context: Context): LeBonCoinDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    if (TEST_MODE) {
                        instance = Room.inMemoryDatabaseBuilder(
                            context,
                            LeBonCoinDataBase::class.java
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                    else {
                        instance = Room.databaseBuilder(
                            context,
                            LeBonCoinDataBase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                    INSTANCE = instance
                }
                return instance
            }
        }

        fun closeAndDestroy() {
            INSTANCE?.close()
            INSTANCE = null
        }
    }
}