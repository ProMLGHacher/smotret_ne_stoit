package com.krea.kollege.feauture.auth.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.krea.kollege.R
import com.krea.kollege.feauture.app_navigation.model.Screen
import com.krea.kollege.feauture.auth.model.AuthState
import com.krea.kollege.feauture.auth.view_model.AuthViewModel

@Composable
fun Auth(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState()
    when(state.value) {
        is AuthState.Auth -> Content(
            state = state.value as AuthState.Auth,
            viewModel = viewModel
        )
        AuthState.Success -> {}
    }
    LaunchedEffect(key1 = state.value) {
        when(state.value) {
            is AuthState.Auth -> {}
            AuthState.Success -> {
                navController.navigate(Screen.Main.route) {
                    launchSingleTop = true
                }
            }
        }
    }

}

@Composable
fun Content(
    state: AuthState.Auth,
    viewModel: AuthViewModel
) {
    Image(
        painter = painterResource(id = R.drawable.back),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.wifi_home),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(0.3f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Smart House",
            color = Color.White,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column(
            modifier = Modifier
                .background(
                    if (state.isLogin) Color(0x88C2B874) else Color(0x88C2B874),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(top = 20.dp, bottom = 40.dp, start = 20.dp, end = 20.dp),
        ) {
            TextField(
                value = state.mail,
                onValueChange = {
                    viewModel.setEmail(it)
                },
                placeholder = {
                    Text("E-mail", color = Color.White)
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    placeholderColor = Color.White,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = !state.isLogin) {
                TextField(
                    value = state.name,
                    onValueChange = {
                        viewModel.setName(it)
                    },
                    modifier = Modifier.fillMaxWidth(0.7f),
                    placeholder = {
                        Text("Name", color = Color.White)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        placeholderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.setPassword(it)
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                placeholder = {
                    Text("Password", color = Color.White)
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    placeholderColor = Color.White,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                      viewModel.submit()
            }, modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF984E4F)
            )
        ) {
            Text(if(state.isLogin) "Enter Your House" else "New Resident", color = Color.White)
        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                viewModel.swapLogReg()
            }, modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF2A2A37)
            )
        ) {
            Text(if (state.isLogin) "Enter Your House" else "New Resident", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (state.error != null) {
            Text(state.error, color = Color.Red)
        }
    }
}