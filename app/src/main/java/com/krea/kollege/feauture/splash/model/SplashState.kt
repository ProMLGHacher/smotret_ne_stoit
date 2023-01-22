package com.krea.kollege.feauture.splash.model

sealed class SplashState {
    object Loading : SplashState()
    object Unauthorized : SplashState()
}
