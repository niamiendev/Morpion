package com.berakahnd.morpion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.berakahnd.morpion.core.navigation.AppNavigation
import com.berakahnd.morpion.ui.theme.MorpionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    App()
}