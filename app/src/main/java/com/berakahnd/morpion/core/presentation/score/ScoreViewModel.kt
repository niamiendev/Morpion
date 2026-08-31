package com.berakahnd.morpion.core.presentation.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berakahnd.morpion.core.data.ScoreItem
import com.berakahnd.morpion.core.data.ScoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScoreViewModel(
    private val scoreRepository: ScoreRepository
) : ViewModel() {

    private val _scores = MutableStateFlow<List<ScoreItem>>(emptyList())
    val scores: StateFlow<List<ScoreItem>> = _scores.asStateFlow()

    init {
        loadScores()
    }

    fun loadScores() {
        viewModelScope.launch {
            _scores.value = scoreRepository.getAllScores()
        }
    }

    fun clearScores() {
        viewModelScope.launch {
            scoreRepository.deleteAllScores()
            _scores.value = emptyList()
        }
    }
}
