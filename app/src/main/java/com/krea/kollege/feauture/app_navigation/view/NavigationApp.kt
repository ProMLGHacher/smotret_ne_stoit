package com.krea.kollege.feauture.app_navigation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.krea.kollege.feauture.add_room.view.AddRoom
import com.krea.kollege.feauture.app_navigation.model.Screen
import com.krea.kollege.feauture.auth.view.Auth
import com.krea.kollege.feauture.main.navigation.view.InAppNavigation
import com.krea.kollege.feauture.splash.view.Splash

@OptIn(ExperimentalPagerApi::class)
@ExperimentalFoundationApi
@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            Splash(
                navController = navController
            )
        }
        composable(Screen.Auth.route) {
            Auth(
                navController = navController
            )
        }
        composable(Screen.Main.route) {
            InAppNavigation(
                appNavController = navController
            )
        }
        composable(Screen.AddRoom.route) {
            AddRoom(
                navController
            )
        }
    }
}