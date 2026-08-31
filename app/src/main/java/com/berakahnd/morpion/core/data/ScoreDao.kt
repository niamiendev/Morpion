package com.berakahnd.morpion.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {
    @Insert
    suspend fun insertScore(score: ScoreItem)
    @Query("SELECT * FROM scores ORDER BY score DESC")
    suspend fun getAllScores(): List<ScoreItem>
    @Query("DELETE FROM scores")
    suspend fun deleteAllScores()
}