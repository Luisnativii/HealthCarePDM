package com.micharlie.healthcare.ui.screens.homeScreen

import android.annotation.SuppressLint
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
 DrawerBar(drawerState = drawerState, sessionState = false )
 {
     Scaffold {
            TopBar(drawerState = drawerState)

     }
 }
}

@Composable
@Preview
fun HomeScreenPreview()
{
    HomeScreen()
}