package com.krea.kollege.feauture.main.settings.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.krea.kollege.R

@ExperimentalMaterialApi
@Composable
fun Settings() {
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar()
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(Color(0xFF2A2A37)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(150.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.android),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.BottomEnd)
                                .offset(15.dp, 15.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF984E4F))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.camera),
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            )
                        }
                    }
                }
            }
            val list = listOf(
                "Username",
                "Email",
                "Phone",
                "Gender",
                "Date Of Birth",
            )
            items(list.size) { index ->
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    Spacer(modifier = Modifier.height(35.dp))
                    Text(list[index], modifier = Modifier.fillMaxWidth())
                    TextField(
                        value = list[index],
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            Color.Black,
                            backgroundColor = Color.Transparent
                        ),
                    )
                }
            }
            item {
                TextButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)) {
                    Text("Sign Out", color = Color.Red)
                }
            }
        }

    }
}



@Composable
fun AppBar() {
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFF707070)))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2A2A37))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = { }, colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Transparent
                )
            ) {
                Text("SAVE", color = Color.Transparent)
            }
            Text("Profile", color = Color.White, fontWeight = FontWeight.Bold)
            TextButton(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Transparent
                )
            ) {
                Text("SAVE", color = Color.White)
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFF707070)))
    }
}