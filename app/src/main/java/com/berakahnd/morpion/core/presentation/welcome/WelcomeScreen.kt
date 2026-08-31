package com.berakahnd.morpion.core.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    onGameClick: () -> Unit = {},
    onScoreClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
){
    Scaffold() { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .rotate(-10f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    buildAnnotatedString {
                        append("T")
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                            )
                        ){
                            append("I")
                        }
                        append("C")
                    }, fontSize = 90.sp, fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                )
                Text(
                    buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                            )
                        ){
                            append("T")
                        }
                        append("A")
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                            )
                        ){
                            append("C")
                        }
                    }, fontSize = 90.sp, fontWeight = FontWeight.ExtraBold
                )
                Text(
                    buildAnnotatedString {
                        append("T")
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                            )
                        ){
                            append("O")
                        }
                        append("E")
                    }, fontSize = 90.sp, fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column {
                CustomButton(
                    text = "Jouer",
                    onClick = onGameClick
                )
                CustomButton(
                    text = "Score",
                    onClick = onScoreClick
                )
                /*CustomButton(
                    text = "Settings",
                    onClick = onSettingsClick
                )*/
            }

        }
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
){
    Button(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 16.dp, vertical = 5.dp),
        onClick = onClick) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text, style = MaterialTheme.typography.titleMedium)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}