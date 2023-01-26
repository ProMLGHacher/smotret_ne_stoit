package com.krea.kollege.feauture.room.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.krea.kollege.R
import com.krea.kollege.feauture.app_navigation.model.Screen
import com.krea.kollege.feauture.room.model.RoomState
import com.krea.kollege.feauture.room.view_model.RoomViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Composable
fun Room(
    navController: NavController,
    viewModel: RoomViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    var selected by viewModel.selected
    val o = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        o.launch {
            val st = viewModel._state.map {
                Log.e("iuiuiu", it.toString())
            }.stateIn(this)
        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.update()
    }
    LaunchedEffect(key1 = true) {
        while (true) {
            delay(100)
            viewModel._state.value = state.value.copy()
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(navController, state.value.name)
        Text(state.value.devices.toString())
        Box(
            modifier = Modifier
                .shadow(34.dp, ambientColor = Color.White)
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .alpha(0f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.light),
                    contentDescription = "",
                    modifier = Modifier.size(45.dp)
                )
                Text("it.name")
            }
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                state.value.devices.forEach {
                    Log.e("IJOI", it.toString())
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = { selected = it.name }) {
                            Icon(
                                painter = painterResource(id = if (it.name == "Light") R.drawable.light else R.drawable.thermostat),
                                contentDescription = "",
                                modifier = Modifier.size(45.dp),
                                tint = if (selected == it.name) Color(0xFF984E4F) else Color(
                                    0xFF707070
                                )
                            )
                        }
                        Text(
                            it.name,
                            color = if (selected == it.name) Color(0xFF984E4F) else Color(0xFF707070)
                        )
                    }
                }
            }
        }
        when (selected) {
            "Light" -> Light(viewModel, state.value)
            else -> Thermostat()
        }
    }
}

@Composable
fun Light(
    viewModel: RoomViewModel,
    state: RoomState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.height(100.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Main lights", fontSize = 30.sp)
                Text(state.name)
            }
            Box(
                modifier = Modifier
                    .shadow(
                        if (state.devices.find { it.name == "Light" }?.isActive!!) 0.dp else 34.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = Color.White
                    )
                    .size(100.dp)
                    .background(
                        if (state.devices.find { it.name == "Light" }?.isActive!!) Color(
                            0xFF984E4F
                        ) else Color.White, shape = RoundedCornerShape(16.dp)
                    )
                    .clip(shape = RoundedCornerShape(16.dp))
                    .clickable {
                        viewModel.switch()
                        viewModel.update()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pover),
                    tint = (if(state.devices.find { it.name == "Light" }?.isActive!!) Color.White else Color.Gray),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(0.7f)
                )
            }
        }
    }
}

@Composable
fun Thermostat() {

}

@Composable
fun AppBar(
    navController: NavController,
    name: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2A2A37))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
        }
        Text(name, color = Color.White, fontWeight = FontWeight.Bold)
        IconButton(onClick = {
            navController.navigate(Screen.AddDevice.route)
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White)
        }
    }
}