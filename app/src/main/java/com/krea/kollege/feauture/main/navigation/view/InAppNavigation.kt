package com.krea.kollege.feauture.main.navigation.view

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.krea.kollege.feauture.main.home.view.Home
import com.krea.kollege.feauture.main.navigation.model.InAppScreen
import com.krea.kollege.feauture.main.settings.view.Settings

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalPagerApi
@Composable
fun InAppNavigation(
    appNavController: NavController
) {
    val items = listOf(
        InAppScreen.Home,
        InAppScreen.Statics,
        InAppScreen.Routines,
        InAppScreen.Settings
    )
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.resourceId),
                                contentDescription = null,
                                modifier = Modifier.fillMaxHeight(0.4f)
                            )
                        },
                        label = { Text(screen.route) },
                        selectedContentColor = Color(0xFF984E4F),
                        unselectedContentColor = Color.Gray,
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = InAppScreen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(InAppScreen.Home.route) {
                Home(
                    appNavController
                )
            }
            composable(InAppScreen.Statics.route) {

            }
            composable(InAppScreen.Routines.route) {

            }
            composable(InAppScreen.Settings.route) {
                Settings()
            }
        }
    }
}