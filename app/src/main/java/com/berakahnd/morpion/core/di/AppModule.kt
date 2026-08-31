package com.berakahnd.morpion.core.di

import androidx.room.Room
import com.berakahnd.morpion.core.data.ScoreDatabase
import com.berakahnd.morpion.core.data.ScoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.berakahnd.morpion.core.presentation.game.GameViewMModel
import com.berakahnd.morpion.core.presentation.score.ScoreViewModel

val scoreModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ScoreDatabase::class.java,
            "score_database"
        ).build()
    }
    single { get<ScoreDatabase>().scoreDao() }
    singleOf(::ScoreRepository)
    viewModel { GameViewMModel(get()) }
    viewModel { ScoreViewModel(get()) }
}

val appModule = listOf(
    scoreModule
)