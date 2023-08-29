package com.example.grabthecrab.ui.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayViewModel @Inject constructor(): ViewModel() {
    private val _stateCoins = MutableStateFlow<Int>(0)
    val stateCoins: StateFlow<Int> = _stateCoins
    fun loadStateCoins(coins: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateCoins.emit(coins)
        }
    }

    private val _stateScore = MutableStateFlow(0)
    val stateScore: StateFlow<Int> = _stateScore
    fun loadStateScore(score: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateScore.emit(score)
        }
    }
}