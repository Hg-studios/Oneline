package com.hg_studio.oneline

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Oneline::class], version = 1)
abstract class OnelineDB: RoomDatabase() {
    abstract fun onelineDao(): OnelineDao
}