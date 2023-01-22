package com.krea.kollege.feauture.splash.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krea.kollege.feauture.splash.model.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val state = _state.asStateFlow()

    init {
        delaySplash()
    }

    fun delaySplash() {
        viewModelScope.launch {
            delay(1000)
            _state.value = SplashState.Unauthorized
        }
    }

}