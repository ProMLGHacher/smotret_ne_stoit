package com.krea.kollege.feauture.add_room.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.krea.kollege.R
import com.krea.kollege.domain.model.RoomType
import com.krea.kollege.feauture.add_room.view_model.AddRoomViewModel

val items = listOf(
    RoomType.TvRoom,
    RoomType.KidRoom,
    RoomType.Bedroom,
    RoomType.LivingRoom,
    RoomType.Kitchen,
    RoomType.Bathroom,
    RoomType.Garage,
    RoomType.Office,
    RoomType.Toilet
)

@ExperimentalFoundationApi
@Composable
fun AddRoom(
    navController: NavController,
    viewModel: AddRoomViewModel = hiltViewModel()
) {
    val state by viewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        AppBar(navController, viewModel)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("Enter Room’s name", color = Color(0xFF979797))
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(value = state.name, onValueChange = {
                viewModel.setName(it)
            }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(30.dp))
            Text("Select room’s icon", color = Color(0xFF979797))
        }
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
                            viewModel.setState(items[index])
                        }
                        .background(
                            if (items[index] == state.type) Color(0xFF984E4F) else Color(
                                0xFFF0F0F0
                            )
                        )
                        .padding(vertical = 15.dp, horizontal = 17.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            tint = if (items[index] == state.type) Color.White else Color(0xFFAFAFAF),
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
}

@Composable
fun AppBar(
    navController: NavController,
    viewModel: AddRoomViewModel
) {
    val co = LocalContext.current
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
                if (viewModel.save()) {
                    navController.popBackStack()
                } else {
                    val s = "Название не должно быть пустым или совпадать с существующими"
                    Toast.makeText(co, s, Toast.LENGTH_SHORT).show()
                }
            }, colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color.Transparent
            )
        ) {
            Text("SAVE", color = Color.White)
        }
    }
}