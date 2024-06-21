package com.micharlie.healthcare.ui.screens.mainScreen

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.micharlie.healthcare.ui.components.cards.BloodGlucoseCard
import com.micharlie.healthcare.ui.components.cards.BloodPressureCard
import com.micharlie.healthcare.ui.components.cards.BodyFatCard
import com.micharlie.healthcare.ui.components.cards.CholesterolCard
import com.micharlie.healthcare.ui.components.cards.HeightCard
import com.micharlie.healthcare.ui.components.cards.MuscularMassCard
import com.micharlie.healthcare.ui.components.cards.WeightCard
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.white
import androidx.compose.runtime.livedata.observeAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun MainScreen(
    sessionState: Boolean = true,
    getVideoViewModel: GetVideoViewModel,
    navController: NavController,

    ) {

    val userData by getVideoViewModel.userData.observeAsState(initial = emptyList())

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val firstUser = userData.firstOrNull()

    val heightStr = firstUser?.height ?: ""
    val height = if (heightStr.isBlank()) {
        0
    } else {
        try {
            heightStr.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }
    val weightStr = firstUser?.weight ?: ""
    val weight = if (weightStr.isBlank()) {
        0
    } else {
        try {
            weightStr.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

    val muscularMassStr = firstUser?.muscularMass ?: ""
    val muscularMass = if (muscularMassStr.isBlank()) {
        0
    } else {
        try {
            muscularMassStr.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

    val bodyFatStr = firstUser?.bodyFat?.toString() ?: ""
    val bodyFat = if (bodyFatStr.isBlank()) {
        0f
    } else {
        try {
            bodyFatStr.toFloat()
        } catch (e: NumberFormatException) {
            0f
        }
    }

    val cholesterolStr = firstUser?.cholesterol ?: ""
    val cholesterol = if (cholesterolStr.isBlank()) {
        0
    } else {
        try {
            cholesterolStr.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

    val bloodGlucoseStr = firstUser?.bloodGlucose ?: ""
    val bloodGlucose = if (bloodGlucoseStr.isBlank()) {
        0f
    } else {
        try {
            bloodGlucoseStr.toFloat()
        } catch (e: NumberFormatException) {
            0f
        }
    }

    val bloodPressureStr = firstUser?.bloodPressure ?: ""

    val bloodPressureValues = if (bloodPressureStr.isBlank()) {
        listOf("0", "0")
    } else {
        bloodPressureStr.split("/")
    }

    val bloodPressureSystolic = try {
        bloodPressureValues.getOrNull(0)?.toInt() ?: 0
    } catch (e: NumberFormatException) {
        0
    }

    val bloodPressureDiastolic = try {
        bloodPressureValues.getOrNull(1)?.toInt() ?: 0
    } catch (e: NumberFormatException) {
        0
    }

    DrawerBar(drawerState = drawerState, sessionState = sessionState, content = {
        Scaffold(bottomBar = { BottomBar() }, topBar = { TopBar(drawerState = drawerState) }) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .background(primary)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = "Check your health metrics!",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 23.sp,
                            color = white
                        )

                        Box(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            HeightCard(navController = navController, height = height)
                        }
                        Box(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            WeightCard(navController = navController, weight = weight)
                        }
                        Box(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            MuscularMassCard(
                                navController = navController, muscularMass = muscularMass
                            )
                        }
                        Box(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            BodyFatCard(navController = navController, bodyFat = bodyFat)
                        }
                        Box(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            CholesterolCard(
                                navController = navController, cholesterol = cholesterol
                            )
                        }
                        Box(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            BloodGlucoseCard(
                                navController = navController, bloodGlucose = bloodGlucose
                            )
                        }
                        Box(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            BloodPressureCard(
                                navController = navController,
                                bloodPressureSystolic = bloodPressureSystolic,
                                bloodPressureDiastolic = bloodPressureDiastolic
                            )
                        }
                    }
                }
            }
        }
    }, navController = navController, getVideoViewModel)
}


