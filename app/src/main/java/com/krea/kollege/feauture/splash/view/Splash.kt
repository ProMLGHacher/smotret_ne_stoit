package com.krea.kollege.feauture.splash.view

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.krea.kollege.R
import com.krea.kollege.feauture.app_navigation.model.Screen
import com.krea.kollege.feauture.splash.model.SplashState
import com.krea.kollege.feauture.splash.view_model.SplashViewModel

@Composable
fun Splash(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    val infiniteTransition = rememberInfiniteTransition()
    val rotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(0.7f)
                .rotate(rotation.value),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )
    }

    LaunchedEffect(key1 = state) {
        when(state) {
            SplashState.Loading -> {}
            SplashState.Unauthorized -> navController.navigate(Screen.Auth.route) {
                popUpTo(Screen.Auth.route) {
                    inclusive = true
                }
            }
            SplashState.Success -> navController.navigate(Screen.Main.route) {
                popUpTo(Screen.Main.route) {
                    inclusive = true
                }
            }
        }
    }
}