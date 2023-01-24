package com.krea.kollege.feauture.room.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Room(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(navController)
    }
}

@Composable
fun AppBar(
    navController: NavController
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
        Text("Add room", color = Color.White, fontWeight = FontWeight.Bold)
        TextButton(
            onClick = {
                navController.popBackStack()
            }, colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color.Transparent
            )
        ) {
            Text("+", color = Color.White)
        }
    }
}