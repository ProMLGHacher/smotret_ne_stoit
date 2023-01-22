package com.krea.kollege.feauture.main.settings.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                        ) {
                            Icon(
                                imageVector = Icons.Default.Face,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.fillMaxSize()
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
            items(list.size) {
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    Spacer(modifier = Modifier.height(35.dp))
                    Text("Username", modifier = Modifier.fillMaxWidth())
                    TextField(
                        value = "",
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
                TextButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Sign Out", color = Color.Red)
                }
            }
        }

    }
}



@Composable
fun AppBar() {
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
}