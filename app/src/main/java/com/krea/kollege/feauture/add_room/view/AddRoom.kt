package com.krea.kollege.feauture.add_room.view

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krea.kollege.R

data class RoomItem(
    val icon: Int,
    val name: String
)

val items = listOf(
    RoomItem(icon = R.drawable.kitchen, name = "Kitchen"),
    RoomItem(icon = R.drawable.bedroom, name = "Bedroom"),
    RoomItem(icon = R.drawable.bathroom, name = "Bathroom"),
    RoomItem(icon = R.drawable.office, name = "Office"),
    RoomItem(icon = R.drawable.tv_room, name = "TV room"),
    RoomItem(icon = R.drawable.living_room, name = "Living room"),
    RoomItem(icon = R.drawable.garage, name = "Garage"),
    RoomItem(icon = R.drawable.toilet, name = "Toilet"),
    RoomItem(icon = R.drawable.kid_room, name = "Kid room"),
)

@ExperimentalFoundationApi
@Composable
fun AddRoom(
    navController: NavController
) {
    var selected by remember {
        mutableStateOf(0)
    }
    AppBar(navController)
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        AppBar(navController)
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text("Enter Room’s name", color = Color(0xFF979797))
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(value = "Kitchen", onValueChange = {}, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(30.dp))
            Text("Select room’s icon", color = Color(0xFF979797))
        }
        LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(horizontal = 10.dp)) {
            items(items.size) { index ->
                Column {
                    Box(modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 2.dp)
                        .fillMaxSize(1f)
                        .clip(CircleShape)
                        .clickable {
                            selected = index
                        }
                        .background(if(index == selected) Color(0xFF984E4F) else Color(0xFFF0F0F0))
                        .padding(vertical = 15.dp, horizontal = 17.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(tint = if(index == selected) Color.White else Color(0xFFAFAFAF),painter = painterResource(id = items[index].icon), contentDescription = items[index].name)
                    }
                    Text(items[index].name, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
            }
        }

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
        TextButton(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = Color.Transparent
        )) {
            Text("SAVE", color = Color.White)
        }
    }
}