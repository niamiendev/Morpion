package com.berakahnd.morpion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.berakahnd.morpion.core.navigation.AppNavigation
import com.berakahnd.morpion.ui.theme.MorpionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MorpionTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    AppNavigation()
}

