package com.krea.kollege.feauture.main.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.krea.kollege.feauture.app_navigation.model.Screen
import com.krea.kollege.feauture.main.home.view_model.AddressViewModel
import com.krea.kollege.feauture.main.home.view_model.RoomsViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

data class TestData(
    val test: String = ""
)

val list = listOf(
    TestData(
        "ROOM"
    )
)

@ExperimentalPagerApi
@Composable
fun Home(
    appNavController: NavController
) {
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(
            navController = appNavController
        )
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
    pagerState: PagerState,
    viewModel: RoomsViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getRooms()
    }
    val state by viewModel.state
    HorizontalPager(count = list.size, state = pagerState) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
        ) {
            items(state.size) { index ->
                Column(
                    modifier = Modifier
                        .shadow(if (state[index].name == viewModel.get()) 0.dp else 34.dp, shape = RoundedCornerShape(20.dp), ambientColor = Color.White)
                        .padding(5.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            viewModel.set(state[index].name)
                            appNavController.navigate(Screen.Room.route)
                        }
                        .background(if (state[index].name == viewModel.get()) Color(0xFF984E4F) else Color.White)
                        .padding(vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = state[index].type.icon),
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = if (state[index].name == viewModel.get()) Color.White else Color(0xFF984E4F)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(state[index].name, color = if (state[index].name == viewModel.get()) Color.White else Color(0xFF984E4F))
                    Text(
                        "×${state[index].listOfDevices.size} Devices",
                        color = if (state[index].name == viewModel.get()) Color.White.copy(0.85f) else Color.Black.copy(
                            0.2f
                        )
                    )
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
fun AppBar(
    navController: NavController,
    viewModel: AddressViewModel = hiltViewModel()
) {
    val state by viewModel.state
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2A2A37))
            .padding(vertical = 20.dp, horizontal = 10.dp)
    ) {
        TextButton({
            navController.navigate(Screen.Map.route)
        }, contentPadding = PaddingValues(0.dp)) {
            Text("Your Home", color = Color.White, fontSize = 25.sp)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "", tint = Color.Gray)
            Text(state.value, color = Color.Gray, fontSize = 12.sp)
        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.update()
    }
}