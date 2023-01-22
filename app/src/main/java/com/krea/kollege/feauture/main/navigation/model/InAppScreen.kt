package com.krea.kollege.feauture.main.navigation.model

import com.krea.kollege.R

sealed class InAppScreen(val route: String, val resourceId: Int) {
    object Home : InAppScreen("Home", R.drawable.home)
    object Statics : InAppScreen("Statics", R.drawable.statics)
    object Routines : InAppScreen("Routines", R.drawable.routines)
    object Settings : InAppScreen("Setting", R.drawable.settings)
}