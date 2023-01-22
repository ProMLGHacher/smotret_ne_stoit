package com.krea.kollege.feauture.main.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.krea.kollege.feauture.app_navigation.model.Screen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

data class TestData(
    val test: String = ""
)

val list = listOf(
    TestData(
        "Комната"
    ),
    TestData(
        "2 Комната"
    )
)

@ExperimentalPagerApi
@Composable
fun Home(
    appNavController: NavController
) {
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar()
        TabBar(
            appNavController,
            pagerState
        )
        Content(
            appNavController,
            pagerState
        )
    }
}

@ExperimentalPagerApi
@Composable
fun Content(
    appNavController: NavController,
    pagerState: PagerState
) {
    HorizontalPager(count = list.size, state = pagerState) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
        ) {
            items(3) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF984E4F))
                        .padding(vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Living Room", color = Color.White)
                    Text("×3 Devices", color = Color.White.copy(0.85f))
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabBar(
    appNavController: NavController,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color(0xFF2A2A37),
            contentColor = Color.White,
            modifier = Modifier.fillMaxWidth(),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(4.dp),
                    color = Color(0xFF984E4F)
                )
            },
            edgePadding = 0.dp
        ) {
            repeat(list.size) {
                Tab(
                    selected = true,
                    text = { Text(list[it].test) },
                    onClick = {
                              scope.launch {
                                  pagerState.animateScrollToPage(it)
                              }
                    },
                )
            }
        }
        IconButton(
            onClick = {
                      appNavController.navigate(Screen.AddRoom.route)
            }, modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.CenterEnd)
                .clip(
                    CircleShape
                )
                .size(35.dp)
                .background(Color(0xFF984E4F))
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White)
        }
    }
}

@Composable
fun AppBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2A2A37))
            .padding(vertical = 20.dp, horizontal = 10.dp)
    ) {
        Text("Your Home", color = Color.White, fontSize = 25.sp)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "", tint = Color.Gray)
            Text("2715 Ash Dr. San Jose, South Dakota 83475", color = Color.Gray, fontSize = 12.sp)
        }
    }
}