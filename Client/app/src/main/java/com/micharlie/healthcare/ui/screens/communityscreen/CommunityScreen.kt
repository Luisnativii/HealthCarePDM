package com.micharlie.healthcare.ui.screens.communityscreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.micharlie.healthcare.ui.theme.contrasPrimary
import com.micharlie.healthcare.ui.theme.black
@Composable
fun CommunityScreen(navController: NavController) {
    val context = LocalContext.current
    val whatsappCommunityLink = remember { "https://chat.whatsapp.com/BuMvgnfoTK9HHuqDEQVxWg" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1D48))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Join Our Community",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappCommunityLink))
                    context.startActivity(intent)
                },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = contrasPrimary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Join our WhatsApp community for the latest updates and discussions.",
                    fontSize = 20.sp,
                    color = black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Click to Join",
                    fontSize = 16.sp,
                    color = black,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}