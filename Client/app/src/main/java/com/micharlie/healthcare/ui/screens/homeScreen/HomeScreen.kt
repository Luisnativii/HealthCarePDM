package com.micharlie.healthcare.ui.screens.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.HomeScreenSelector
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.theme.primary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
 DrawerBar(drawerState = drawerState, sessionState = true )
 {
     Scaffold (topBar = {TopBar(drawerState = drawerState) }, bottomBar = { BottomBar()}){
           Column(modifier = Modifier
               .padding(it)
               .fillMaxWidth()
               .fillMaxHeight()
               .background(color = primary))
           {
               HomeScreenContent()


           }

     }
 }
}
@Composable
fun HomeScreenContent(){
    HomeScreenSelector()

}


@Composable
@Preview
fun HomeScreenPreview()
{
    HomeScreen()
}
