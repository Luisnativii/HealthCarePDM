package com.micharlie.healthcare.ui.screens.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.cards.BloodGlucoseCard
import com.micharlie.healthcare.ui.cards.BloodPressureCard
import com.micharlie.healthcare.ui.cards.BodyFatCard
import com.micharlie.healthcare.ui.cards.CholesterolCard
import com.micharlie.healthcare.ui.cards.HeightCard
import com.micharlie.healthcare.ui.cards.MuscularMassCard
import com.micharlie.healthcare.ui.cards.WeightCard
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    DrawerBar(drawerState = drawerState, sessionState = true , content = {
        Scaffold(
            bottomBar = { BottomBar() },
            topBar = { TopBar(drawerState = drawerState) }
        ) {
            Column (
                modifier = Modifier
                    .padding(it)
                    .background(primary)
            ){
                LazyColumn (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item {
                        Text(
                            text = "Check your health metrics!",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 23.sp,
                            color = white
                        )

                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                        ){
                            HeightCard(navController = navController)
                        }
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                        ){
                            WeightCard(navController = navController)
                        }
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                        ){
                            MuscularMassCard(navController = navController)
                        }
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                        ){
                            BodyFatCard(navController = navController)
                        }
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                        ){
                            CholesterolCard(navController = navController)
                        }
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                        ){
                            BloodGlucoseCard(navController = navController)
                        }
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                        ){
                            BloodPressureCard(navController = navController)
                        }
                    }
                }
            }
        }
    }, navController = navController  )
}
@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}