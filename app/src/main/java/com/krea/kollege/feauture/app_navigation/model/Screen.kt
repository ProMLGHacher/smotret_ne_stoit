package com.krea.kollege.feauture.app_navigation.model

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Auth : Screen("auth_screen")
    object Main : Screen("main")
    object AddRoom : Screen("add_room")
    object Map : Screen("map")
    object Room : Screen("room")
    object AddDevice : Screen("Aad_device")
    object Ebatnya : Screen("Ebatnya")
}
