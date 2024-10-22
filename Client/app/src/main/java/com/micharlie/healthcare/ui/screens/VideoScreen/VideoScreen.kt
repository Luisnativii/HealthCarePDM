package com.micharlie.healthcare.ui.screens.VideoScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.micharlie.healthcare.ui.components.YoutubePlayer
import com.micharlie.healthcare.ui.login.SharedPreferencesManager
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.secondary
import com.micharlie.healthcare.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VideoScreen(
    navController: NavController,
    url: String,
    name: String,
    sessionState: Boolean = true,
    category: String,
    getVideoViewModel: GetVideoViewModel,
    sharedPreferencesManager: SharedPreferencesManager
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var videos by remember { mutableStateOf(listOf<VideoApi>()) }

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
    videos = videos.filter { it.category == category }
    DrawerBar(drawerState = drawerState,
        sessionState = remember { mutableStateOf(false) },
        navController = navController,
        getVideoViewModel = getVideoViewModel,
        sharedPreferencesManager = sharedPreferencesManager,
        content = {
            Scaffold(topBar = { TopBar(drawerState = drawerState) }, bottomBar = { BottomBar() }) {

                Column(
                    modifier = Modifier
                        .padding(it)
                        .background(primary)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
//                    Text(
//                        text = name,
//                        fontSize = 20.sp,
//                        color = white,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center
//                    )
                    Box(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxSize()
                            .padding(0.dp, 10.dp, 0.dp, 0.dp)
                            .clip(
                                RoundedCornerShape(4.dp)
                            )
                            .background(secondary)
                    ) {
                        YoutubePlayer(videoId = url)
                    }
                    Column(modifier = Modifier.padding()) {

                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(videos.size) { it1 ->
                                //VideoCard()
                                VideoCard(
                                    videoId = videos[it1].link!!,
                                    videoCategory = videos[it1].category!!,
                                    videoImageUrl = videos[it1].videoBanner!!,
                                    imageChannel = videos[it1].channelPhoto!!,
                                    videoTitle = videos[it1].videoName!!,
                                    userChannel = videos[it1].channelName!!,
                                    navController,
                                )

                            }
                        }
                    }

                }

            }
        })
}


fun extractYoutubeVideoId(url: String): String? {
    val regex =
        "(?<=v=|/videos/|embed/|youtu.be/|/v/|/e/|watch\\?v=|watch\\?vi=|watch\\?v%3D|watch\\?vi%3D|youtu.be/|%2Fvideos%2F|embed%2F|youtu.be%2F|youtu.be%2F|/v%2F|/e%2F|youtu.be%2F|youtu.be%2F|youtu.be%2F|youtu.be%2F|%3Fv%3D|&v=|/v=|/videos=|embed|youtu.be=|%2Fvideos|embed|youtu.be|%2Fv=|%2Fe=|youtu.be|youtu.be|youtu.be)([\\w-]{11})".toRegex()
    val matchResult = regex.find(url)
    return matchResult?.value
}
