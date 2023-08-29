package com.example.grabthecrab.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {
    private val _stateMusic = MutableStateFlow<Boolean>(true)
    val stateMusic: StateFlow<Boolean> = _stateMusic
    fun loadStateMusic(music: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMusic.emit(music)
        }
    }

    private val _stateSound = MutableStateFlow<Boolean>(true)
    val stateSound: StateFlow<Boolean> = _stateSound
    fun loadStateSound(sound: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateSound.emit(sound)
        }
    }

    private val _stateSetting = MutableStateFlow(false)
    val stateSetting: StateFlow<Boolean> = _stateSetting

    fun loadStateSetting(setting: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateSetting.emit(setting)
        }
    }

}