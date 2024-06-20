package com.micharlie.healthcare.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.micharlie.healthcare.ui.navigation.ScreenRoute
import com.micharlie.healthcare.ui.theme.contrast1
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.secondary
import com.micharlie.healthcare.ui.theme.tertiary
import com.micharlie.healthcare.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoCard(
    videoId: String,
    videoCategory: String,
    videoImageUrl: String,
    imageChannel: String,
    videoTitle: String,
    userChannel: String,
    navController: NavController
) {

    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(350.dp), colors = CardDefaults.cardColors(containerColor = secondary),
        onClick = { navController.navigate(ScreenRoute.VideoScreen.route + "/{$videoId}") }
    ) {
        Box(modifier = Modifier) {
            AsyncImage(
                model = videoImageUrl,
                contentDescription = "", contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(contrast2)
                    , horizontalArrangement = Arrangement
                    .SpaceAround, verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = imageChannel,
                    contentDescription = "", contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                )


                Text(
                    text = userChannel, fontSize = 20.sp, textAlign = TextAlign.Center,
                    color = white, fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = videoCategory,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = white,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Text(
                text = videoTitle, modifier = Modifier.padding(8.dp), fontSize =
                25.sp, fontWeight = FontWeight.Light, color = white
            )
        }
    }


}

@Composable
@Preview
fun VideoCardPreview() {
    VideoCard(
        userChannel = "MicharlieFit",
        videoCategory = "HIIT",
        videoTitle = "Video Title",
        videoImageUrl = "https://i.ytimg.com/vi/1y6smkh6c-0/maxresdefault.jpg",
        imageChannel = "https://yt3.ggpht.com/ytc/AKedOLQ2J9z1z1Z9Z9z1z1Z9Z9z1z1Z9Z9z1z1Z9z1z1Z9",
        navController = rememberNavController(), videoId = "1y6smkh6c-0"
    )
}