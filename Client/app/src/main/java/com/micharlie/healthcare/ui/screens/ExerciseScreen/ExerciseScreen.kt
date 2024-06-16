package com.micharlie.healthcare.ui.screens.ExerciseScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.theme.primary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExerciseScreen(navController: NavController, sessionState: Boolean = true) {
    val drawerState = rememberDrawerState(initialValue =DrawerValue.Closed)
    DrawerBar(drawerState = drawerState,
        sessionState = sessionState,
        navController = navController,
        content = {
            // Content of the drawer
            Scaffold(bottomBar = { BottomBar()},
                topBar = { TopBar(drawerState = drawerState)},
                )
            {
                // Content of the screen
                Column(modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(primary)) {
                    SearchBar()
                    LazyColumn {
                        items(10) {
                            //

                        }
                    }

                }

            }
        })
}

@Composable
@Preview
fun ExerciseScreenPreview() {
    ExerciseScreen(rememberNavController(), sessionState = true)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(){
    // Search bar
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        androidx.compose.material3.SearchBar(
            query = "",
            onQueryChange ={} ,
            onSearch = {},
            active = true,
            onActiveChange = {}
        ) {

        }
    }
}

