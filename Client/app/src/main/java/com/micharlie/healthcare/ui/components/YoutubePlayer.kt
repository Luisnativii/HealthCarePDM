package com.micharlie.healthcare.ui.components

import android.annotation.SuppressLint
import android.graphics.Bitmap

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch


@Composable
fun YoutubePlayer(videoId: String) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val youTubePlayerView = remember { mutableStateOf<YouTubePlayerView?>(null) }

    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        coroutineScope.launch {
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    }
                })
            }.also { youTubePlayerView.value = it }
        },
        modifier = Modifier.fillMaxSize(),
        update = { view ->
            // No operation needed here
        }
    )

    DisposableEffect(Unit) {
        onDispose {
            coroutineScope.launch {
                // Release the player when the composable is disposed
                youTubePlayerView.value?.release()
            }
        }
    }
}




@Composable
@Preview
fun YoutubePlayerPreview() {
    YoutubePlayer(videoId ="bzRljXaqyLc")
}