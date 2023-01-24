package com.krea.kollege.feauture.room.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.krea.kollege.R
import com.krea.kollege.feauture.main.home.view_model.AddressViewModel
import com.krea.kollege.feauture.room.view_model.RoomViewModel

@Composable
fun Room(
    navController: NavController,
    viewModel: RoomViewModel = hiltViewModel()
) {
    val state by viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(navController, state.value.name)
        Box(modifier = Modifier
            .shadow(34.dp, ambientColor = Color.White)
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 10.dp)
        ) {
            Row {
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.light), contentDescription = "", modifier = Modifier.size(45.dp))
                    Text("Light")
                }
                Column(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.thermostat), contentDescription = "", modifier = Modifier.size(45.dp))
                    Text("Thermostat")
                }
            }
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
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White)
        }
    }
}