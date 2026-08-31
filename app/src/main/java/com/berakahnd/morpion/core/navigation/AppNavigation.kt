package com.berakahnd.morpion.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.berakahnd.morpion.core.presentation.game.GameScreen
import com.berakahnd.morpion.core.presentation.score.ScoreScreen
import com.berakahnd.morpion.core.presentation.welcome.WelcomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Welcome
    ){
        composable<Screens.Welcome>{
            WelcomeScreen(
                onGameClick = {
                    navController.navigate(Screens.Game)
                },
                onScoreClick = {
                    navController.navigate(Screens.Score)
                },
                onSettingsClick = {
                    navController.navigate(Screens.Settings)
                }
            )
        }
        composable<Screens.Game>{
            GameScreen()
        }
        composable<Screens.Score>{
            ScoreScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable<Screens.Settings>{

        }
    }
}