package com.micharlie.healthcare.ui.components

import android.provider.MediaStore.Video
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.theme.black
import com.micharlie.healthcare.ui.theme.contrast1
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.white

@Composable
fun HomeScreenCard(text1: String, text2: String, imageId: Int){
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(primary)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = text1, color = white,fontSize = 25.sp, fontWeight = FontWeight.Bold )
            Text(text = text2, color = white,fontSize = 20.sp,
                fontWeight = FontWeight.Light, modifier = Modifier.padding(10.dp))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(primary)){
            Button(onClick = { /*TODO*/ }, modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors(containerColor = contrast2, contentColor =
                white
                ) ) {
                Text(text = "Login")

            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors(containerColor = contrast1, contentColor =
                white
                )) {
                Text(text = "Sign Up", color = black)

            }
            Image(painter = painterResource(id = imageId), contentDescription =
            "placeholder", contentScale = ContentScale.FillHeight, modifier = Modifier.padding(0
                .dp,20.dp,0.dp,0.dp))
        }


    }

}

