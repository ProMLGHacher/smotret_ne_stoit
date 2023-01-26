package com.krea.kollege.feauture.app_navigation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.krea.kollege.feauture.add_device.view.AddDevice
import com.krea.kollege.feauture.add_room.view.AddRoom
import com.krea.kollege.feauture.app_navigation.model.Screen
import com.krea.kollege.feauture.auth.view.Auth
import com.krea.kollege.feauture.main.navigation.view.InAppNavigation
import com.krea.kollege.feauture.map.view.Map
import com.krea.kollege.feauture.room.view.Room
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
        composable(Screen.Map.route) {
            Map(
                navController = navController
            )
        }
        composable(Screen.Room.route) {
            Room(
                navController = navController
            )
        }
        composable(Screen.AddDevice.route) {
            AddDevice(
                navController = navController
            )
        }
    }
}