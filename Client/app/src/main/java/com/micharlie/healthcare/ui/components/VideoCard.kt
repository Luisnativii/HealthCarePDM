package com.micharlie.healthcare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.theme.contrasPrimary
import com.micharlie.healthcare.ui.theme.contrast1
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.secondary
import com.micharlie.healthcare.ui.theme.white

@Composable
fun VideoCard( ) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(350.dp)
        , colors = CardDefaults.cardColors(containerColor = secondary)
    ) {
        Box(modifier = Modifier){
            Image(painter = painterResource(id = R.drawable.placeholder), contentDescription ="",
                modifier = Modifier.height(150.dp), contentScale = ContentScale
                    .FillWidth )
        }
       Column(horizontalAlignment = Alignment.CenterHorizontally){

           Row(modifier = Modifier
               .fillMaxWidth()
               .padding(8.dp), horizontalArrangement = Arrangement
               .SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
               Image(painter = painterResource(id = R.drawable.placeholder), contentDescription = "",
                   modifier = Modifier
                       .clip(CircleShape)
                       .size(80.dp), contentScale = ContentScale.FillHeight)


               Text(text = "userSampleName", fontSize = 15.sp, textAlign = TextAlign.Center,
                   color = white, fontWeight = FontWeight.W500)
               Button(onClick = { /*TODO*/ },
                   colors = ButtonDefaults.buttonColors(containerColor = contrast2)) {
                   Text("Hiit", fontWeight = FontWeight.Bold)
               }
           }
              Text(text = "Title video sample", modifier = Modifier.padding(8.dp), fontSize =
              25.sp, fontWeight = FontWeight.Normal, color = white)
       }
    }


}

@Composable
@Preview
fun VideoCardPreview() {
    VideoCard()
}