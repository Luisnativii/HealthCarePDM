package com.micharlie.healthcare.ui.screens.ExerciseScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.data.api.VideoApi
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.components.VideoCard
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoState
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.login.SharedPreferencesManager
import com.micharlie.healthcare.ui.navigation.ScreenRoute
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.secondary
import com.micharlie.healthcare.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExerciseScreen(
    navController: NavController, sessionState: Boolean = true, getVideoViewModel: GetVideoViewModel, sharedPreferencesManager: SharedPreferencesManager
) {
    var cat by remember {
        mutableIntStateOf(0)
    }
    var videos by remember { mutableStateOf(listOf<VideoApi>()) }
    var filteredVideos by remember { mutableStateOf(listOf<VideoApi>()) }

    LaunchedEffect(getVideoViewModel) {
        getVideoViewModel.getVideoState.collect { state ->
            when (state) {
                is GetVideoState.Success -> {
                    videos = state.videos
                }
                // Manejar otros estados si es necesario
                else -> {}
            }
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    DrawerBar(drawerState = drawerState,
        sessionState = sessionState,
        navController = navController,
        getVideoViewModel = getVideoViewModel,
        sharedPreferencesManager = sharedPreferencesManager,
        content = {
            // Content of the drawer
            Scaffold(
                bottomBar = { BottomBar() },
                topBar = { TopBar(drawerState = drawerState) },
            ) { it ->
                // Content of the screen
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(primary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Categorias",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = white
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {
                                    filteredVideos = videos.filter { it.category == "1" }
                                },
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(2.dp, 0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = contrast2)
                            ) {
                                Text(
                                    text = "HIIT", fontWeight = FontWeight.Medium, fontSize = 11.sp
                                )

                            }
                            Button(
                                onClick = {
                                    filteredVideos = videos.filter {
                                        it.category == "2"
                                    }
                                    println(filteredVideos)
                                },
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(2.dp, 0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = contrast2)
                            ) {
                                Text(
                                    text = "INTERMEDIO",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 11.sp
                                )

                            }
                            Button(
                                onClick = {
                                    filteredVideos = videos.filter {
                                        it.category == "3"
                                    }
                                },
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(2.dp, 0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = contrast2)
                            ) {
                                Text(
                                    text = "PRINCIPIANTE",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 11.sp
                                )

                            }

                        }
                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        items(filteredVideos.size) { it1 ->
                            //VideoCard()
                            VideoCard(
                                videoId = filteredVideos[it1].link!!,
                                videoCategory = filteredVideos[it1].category!!,
                                videoImageUrl = filteredVideos[it1].videoBanner!!,
                                imageChannel = filteredVideos[it1].channelPhoto!!,
                                videoTitle = filteredVideos[it1].videoName!!,
                                userChannel = filteredVideos[it1].channelName!!,
                                navController,
                            )

                        }
                    }

                }

            }
        })
}



