package com.berakahnd.morpion.core.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berakahnd.morpion.core.data.ScoreItem
import com.berakahnd.morpion.core.data.ScoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GameViewMModel(
    private val scoreRepository: ScoreRepository
) : ViewModel() {
    private val _board = MutableStateFlow(List(9) { "" })
    val board: StateFlow<List<String>> = _board.asStateFlow()

    private val _isPlayerXTurn = MutableStateFlow(true)
    val isPlayerXTurn: StateFlow<Boolean> = _isPlayerXTurn.asStateFlow()

    private val _winner = MutableStateFlow<String?>(null)
    val winner: StateFlow<String?> = _winner.asStateFlow()

    private val _isDraw = MutableStateFlow(false)
    val isDraw: StateFlow<Boolean> = _isDraw.asStateFlow()

    fun onCellClicked(index: Int) {
        if (_board.value[index].isNotEmpty() || _winner.value != null || _isDraw.value) return

        val currentPlayer = if (_isPlayerXTurn.value) "X" else "O"
        
        _board.update { currentBoard ->
            currentBoard.toMutableList().also { it[index] = currentPlayer }
        }

        val gameWinner = checkWinner(_board.value)
        if (gameWinner != null) {
            _winner.value = gameWinner
            saveScore(gameWinner)
        } else if (_board.value.none { it.isEmpty() }) {
            _isDraw.value = true
        } else {
            _isPlayerXTurn.value = !_isPlayerXTurn.value
        }
    }

    fun resetGame() {
        _board.value = List(9) { "" }
        _isPlayerXTurn.value = true
        _winner.value = null
        _isDraw.value = false
    }

    private fun checkWinner(board: List<String>): String? {
        val winPatterns = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )
        for (pattern in winPatterns) {
            if (board[pattern[0]].isNotEmpty() &&
                board[pattern[0]] == board[pattern[1]] &&
                board[pattern[0]] == board[pattern[2]]
            ) {
                return board[pattern[0]]
            }
        }
        return null
    }

    private fun saveScore(player: String) {
        viewModelScope.launch {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val scoreItem = ScoreItem(
                player = player,
                score = 1,
                date = dateFormat.format(Date())
            )
            scoreRepository.insertScore(scoreItem)
        }
    }
}
