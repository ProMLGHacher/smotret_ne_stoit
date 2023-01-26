package com.krea.kollege.feauture.room.view

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.krea.kollege.R
import com.krea.kollege.feauture.app_navigation.model.Screen
import com.krea.kollege.feauture.room.model.RoomState
import com.krea.kollege.feauture.room.view_model.RoomViewModel

@Composable
fun Room(
    navController: NavController,
    viewModel: RoomViewModel = hiltViewModel()
) {
    val state = viewModel._state
    var selected by viewModel.selected
    LaunchedEffect(key1 = true) {
        viewModel.update()
    }
    LaunchedEffect(key1 = selected) {
        Log.e("dd", "dd")
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
                        IconButton(onClick = {
                            selected = it.name
                            viewModel.ok()
                        }) {
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
            "Light" -> Light(viewModel, state.value) {
                viewModel.switch()
                navController.navigate(Screen.Ebatnya.route)
            }
            "Thermostat" -> Thermostat(viewModel, state.value) {
                viewModel.switch()
                navController.navigate(Screen.Ebatnya.route)
            }
            else -> {}
        }
    }
}

@Composable
fun Light(
    viewModel: RoomViewModel,
    state: RoomState,
    ok: () -> Unit
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
                        ok()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pover),
                    tint = (if (state.devices.find { it.name == "Light" }?.isActive!!) Color.White else Color.Gray),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(0.7f)
                )
            }
        }
    }
}

@Composable
fun Thermostat(
    viewModel: RoomViewModel,
    state: RoomState,
    ok: () -> Unit
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.height(100.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Thermostat", fontSize = 30.sp)
                Text(state.name)
            }
            CustomSwitch(
                isOn = state.devices.find { it.name == "Thermostat" }?.isActive!!,
                switch = {
                    ok()
                },
            )
        }
    }
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

@Composable
fun CustomSwitch(
    scale: Float = 2f,
    width: Dp = 36.dp,
    height: Dp = 20.dp,
    checkedTrackColor: Color = Color(0xFF984E4F),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    thumbColor: Color = Color.White,
    gapBetweenThumbAndTrackEdge: Dp = 4.dp,
    isOn: Boolean,
    switch: () -> Unit
) {

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move the thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (isOn)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .padding(end = 10.dp)
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        switch()
                    }
                )
            }
    ) {

        // Track
        drawRoundRect(
            color = if (isOn) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx())
        )

        // Thumb
        drawCircle(
            color = thumbColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )

    }
}