package com.example.grabthecrab.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grabthecrab.util.RemoteScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemoteScreenViewModel @Inject constructor(): ViewModel(){
    private val _stateRemoteScreen = MutableStateFlow<RemoteScreen>(RemoteScreen.ONBOARDING)
    val stateRemoteScreen: StateFlow<RemoteScreen> = _stateRemoteScreen
    fun loadState(remoteScreen: RemoteScreen) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateRemoteScreen.emit(remoteScreen)
        }
    }
}