package com.berakahnd.morpion.core.presentation.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen() {
    val width = LocalConfiguration.current.screenWidthDp - 32
    val widthButton = (width / 3) - 16

    var board by rememberSaveable { mutableStateOf(List(9) { "" }) }

    var isPlayerXTurn by rememberSaveable { mutableStateOf(true) }
    val currentPlayer = if (isPlayerXTurn) "X" else "O"

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameSectionPlayer(
                    isPlayerXTurn = !isPlayerXTurn,
                    modifier = Modifier.weight(.5f),
                    player = "0",
                    color = Color.Green
                )
                GameSectionPlayer(
                    isPlayerXTurn = isPlayerXTurn,
                    modifier = Modifier.weight(.5f),
                    player = "X",
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (isPlayerXTurn) Arrangement.End else Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = null)
                    Text(text="C'est à votre tour")

                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.width(width.dp)
            ) {
                FlowRow(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = 3
                ) {
                    board.forEachIndexed { index, value ->
                        GameButton(
                            modifier = Modifier.size(widthButton.dp),
                            playerText = value,
                        ) {
                            if (value.isEmpty()) {
                                board = board.toMutableList().also {
                                    it[index] = currentPlayer
                                }
                                isPlayerXTurn = !isPlayerXTurn
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun GameSectionPlayer(
    isPlayerXTurn: Boolean = true,
    modifier: Modifier = Modifier,
    player: String,
    color: Color = MaterialTheme.colorScheme.primary
){
    Surface (
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = if (isPlayerXTurn) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.secondary.copy(alpha = .2f)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding( 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Joueur",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
            )
            Text(
                text = player,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier,
                color = color,
            )
        }
    }
}
@Composable
fun GameButton(
    modifier: Modifier = Modifier,
    playerText: String = "",
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = playerText, fontSize = 70.sp,
            fontWeight = FontWeight.ExtraBold, color = if (playerText == "X") Color.Red else Color.Green
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}