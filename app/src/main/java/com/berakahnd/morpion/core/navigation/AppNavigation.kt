package com.berakahnd.morpion.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.berakahnd.morpion.core.presentation.game.GameScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Game
    ){
        composable<Screens.Welcome>{

        }
        composable<Screens.Game>{
            GameScreen()
        }
        composable<Screens.Score>{

        }
        composable<Screens.Settings>{

        }
    }
}