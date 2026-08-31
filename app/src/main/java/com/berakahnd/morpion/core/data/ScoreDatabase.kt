package com.berakahnd.morpion.core.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScoreItem::class], version = 1, exportSchema = false)
abstract class ScoreDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao
}