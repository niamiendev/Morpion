package com.berakahnd.morpion.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class ScoreItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val player: String,
    val score: Int,
    val date: String
)
