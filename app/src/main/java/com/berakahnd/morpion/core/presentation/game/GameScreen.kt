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
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(
    viewModel: GameViewMModel = koinViewModel()
) {
    val width = LocalConfiguration.current.screenWidthDp - 32
    val widthButton = (width / 3) - 16

    val board by viewModel.board.collectAsState()
    val isPlayerXTurn by viewModel.isPlayerXTurn.collectAsState()
    val winner by viewModel.winner.collectAsState()
    val isDraw by viewModel.isDraw.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tic - Tac - Toe",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameSectionPlayer(
                    isSelected = !isPlayerXTurn,
                    modifier = Modifier.weight(.5f),
                    player = "O",
                    color = Color.Green
                )
                GameSectionPlayer(
                    isSelected = isPlayerXTurn,
                    modifier = Modifier.weight(.5f),
                    player = "X",
                    color = Color.Red
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            if (winner == null && !isDraw) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isPlayerXTurn) Arrangement.End else Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = null)
                        Text(text = "C'est à votre tour")
                    }
                }
            } else {
                Text(
                    text = if (winner != null) "Vainqueur : $winner !" else "Match nul !",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (winner == "X") Color.Red else if (winner == "O") Color.Green else Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
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
                            viewModel.onCellClicked(index)
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            IconButton(
                onClick = {
                    viewModel.resetGame()
                },
                modifier = Modifier.size(64.dp)
            ){
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
        }
    }
}

@Composable
fun GameSectionPlayer(
    isSelected: Boolean = true,
    modifier: Modifier = Modifier,
    player: String,
    color: Color = MaterialTheme.colorScheme.primary
){
    Surface (
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = if (isSelected) MaterialTheme.colorScheme.primary
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
            fontWeight = FontWeight.ExtraBold,
            color = if (playerText == "X") Color.Red else Color.Green
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}