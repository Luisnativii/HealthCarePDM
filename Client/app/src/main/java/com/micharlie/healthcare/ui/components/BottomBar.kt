package com.micharlie.healthcare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.theme.primary

@Composable
fun BottomBar (){

    BottomAppBar(containerColor = primary) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(painter = painterResource(id = R.drawable.logo_blue), contentDescription = "logo_blue")
            Text(text = "© MiCharlieDevs. All Rights Reserved.", color = Color.Gray)
        }

    }
}

@Composable
@Preview
fun BottomBarPreview()
{
    BottomBar()
}
