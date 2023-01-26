package com.krea.kollege.feauture.add_device.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.krea.kollege.R
import com.krea.kollege.feauture.add_device.view_model.AddDeviceViewModel

data class DeviceType(
    val name: String,
    val icon: Int
)

val items = listOf(
    DeviceType(
        name = "Light",
        icon = R.drawable.light
    ),
    DeviceType(
        name = "Thermostat",
        icon = R.drawable.thermostat
    )
)

@Composable
fun AddDevice(
    navController: NavController,
    viewModel: AddDeviceViewModel = hiltViewModel()
) {
    val state by viewModel.state
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(items.size) { index ->
            Column {
                Box(modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 2.dp)
                    .fillMaxSize(1f)
                    .clip(CircleShape)
                    .clickable {
                        viewModel.add(
                            items[index].name
                        )
                        navController.popBackStack()
                    }
                    .background(
                        if (items[index].name == state.value) Color(0xFF984E4F) else Color(
                            0xFFF0F0F0
                        )
                    )
                    .padding(vertical = 25.dp, horizontal = 17.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        tint = if (items[index].name == state.value) Color.White else Color(0xFFAFAFAF),
                        painter = painterResource(id = items[index].icon),
                        contentDescription = items[index].name,
                    )
                }
                Text(
                    items[index].name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}