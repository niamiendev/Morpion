package com.berakahnd.morpion.core.data

class ScoreRepository(
    private val scoreDao: ScoreDao
) {
    suspend fun insertScore(score: ScoreItem) {
        scoreDao.insertScore(score)
    }

    suspend fun getAllScores(): List<ScoreItem> {
        return scoreDao.getAllScores()
    }

    suspend fun deleteAllScores() {
        scoreDao.deleteAllScores()
    }
}