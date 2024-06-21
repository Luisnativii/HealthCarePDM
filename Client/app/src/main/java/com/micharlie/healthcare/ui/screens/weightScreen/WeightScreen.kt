package com.micharlie.healthcare.ui.screens.weightScreen

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.components.historyCards.HistoryWeightCard
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.login.SharedPreferencesManager
import com.micharlie.healthcare.ui.theme.cardsBackgroud
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.weightProgress
import com.micharlie.healthcare.ui.theme.weightProgressBackground
import com.micharlie.healthcare.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeightScreen(
    navController: NavController,
    sessionState: Boolean = true,
    getVideoViewModel: GetVideoViewModel,

) {
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val token = sharedPreferencesManager.getToken()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var weight by remember { mutableIntStateOf(65) }
    var date by remember { mutableStateOf("2023-06-01") }
    DrawerBar(drawerState = drawerState, sessionState = sessionState, content = {
        Scaffold(bottomBar = { BottomBar() }, topBar = { TopBar(drawerState = drawerState) }) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .background(primary)
            ) {
                // Content of the screen
                LazyColumn {
                    item {
                        // Card Icon and Tittle Weight
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .height(100.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = cardsBackgroud
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.hugeiconsweightscale__1_),
                                    contentDescription = "Weight Icon",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .background(
                                            color = weightProgressBackground,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .clip(RoundedCornerShape(15.dp))
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = "Weight",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontSize = 20.sp,
                                    color = white,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(all = 16.dp)
                                )
                            }
                        }


                        // Card input Weight
                        var weightInput by remember { mutableStateOf("65") }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .height(150.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = cardsBackgroud
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    TextField(
                                        value = weightInput, // Mandando a llamar el peso
                                        onValueChange = { weightInput = it },
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                                    )
                                    Text(
                                        text = "kg",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontSize = 16.sp,
                                        color = white,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = onClick@{
                                        val weightInt = weightInput.toIntOrNull() ?: return@onClick
                                        val token = sharedPreferencesManager.getToken()
                                        if (token != null) {
                                            getVideoViewModel.updateWeight(token, weightInt)
                                            getVideoViewModel.getUsersData(token)
                                        } else {
                                            println("Error: Token is null")
                                        }

                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = contrast2),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("Update")
                                }
                            }
                        }

                        // Card
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .width(75.dp)
                                    .height(105.dp)
                                    .padding(end = 8.dp), // Spacing between cards
                                colors = CardDefaults.cardColors(
                                    containerColor = cardsBackgroud
                                )
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = weightInput, // Cambiar por una variable de usuario o db
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = white
                                        )
                                        Text(
                                            text = "kg",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }

                            // Card Normal and LinerProgressIndicator
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(105.dp)
                                    .padding(start = 8.dp), // Spacing between cards
                                colors = CardDefaults.cardColors(
                                    containerColor = cardsBackgroud
                                )
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Normal", // Se tiene que validar para ver que le vamos a poner
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = white
                                        )

                                        Spacer(modifier = Modifier.height(8.dp))

                                        LinearProgressIndicator(
                                            progress = 65 / 100f,
                                            color = weightProgress,
                                            trackColor = weightProgressBackground,
                                            modifier = Modifier
                                                .height(16.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                    }
                                }
                            }
                        }

                        val pointsData = listOf(
                            70f, 68f, 69f, 67f, 65f, 66f, 68f, 70f, 72f, 71f, 70f, 69f
                        )
                        val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .height(300.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = cardsBackgroud
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                Canvas(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                ) {
                                    val maxWeight = pointsData.maxOrNull() ?: 100f
                                    val minWeight = pointsData.minOrNull() ?: 0f
                                    val range = maxWeight - minWeight
                                    val spacePerMonth = size.width / (pointsData.size - 1)
                                    val heightRatio = size.height / range
                                    val numberOfLines = 6

                                    // Draw horizontal lines
                                    for (i in 0..numberOfLines) {
                                        val y = size.height / numberOfLines * i
                                        drawLine(
                                            color = Color.Gray,
                                            start = androidx.compose.ui.geometry.Offset(0f, y),
                                            end = androidx.compose.ui.geometry.Offset(size.width, y),
                                            strokeWidth = 1.dp.toPx()
                                        )
                                    }

                                    // Draw the weight line
                                    val path = Path().apply {
                                        moveTo(0f, size.height - (pointsData[0] - minWeight) * heightRatio)
                                        pointsData.forEachIndexed { index, weight ->
                                            val x = index * spacePerMonth
                                            val y = size.height - (weight - minWeight) * heightRatio
                                            lineTo(x, y)
                                        }
                                    }

                                    drawPath(
                                        path = path,
                                        color = weightProgress,
                                        style = Stroke(width = 4.dp.toPx())
                                    )

                                    pointsData.forEachIndexed { index, weight ->
                                        val x = index * spacePerMonth
                                        val y = size.height - (weight - minWeight) * heightRatio
                                        drawCircle(
                                            color = weightProgress,
                                            radius = 4.dp.toPx(),
                                            center = androidx.compose.ui.geometry.Offset(x, y)
                                        )
                                    }

                                    // Draw the selected weight circle
                                    val selectedWeight = pointsData[8]
                                    val selectedX = 8 * spacePerMonth
                                    val selectedY = size.height - (selectedWeight - minWeight) * heightRatio

                                    drawCircle(
                                        color = Color.Gray,
                                        radius = 6.dp.toPx(),
                                        center = androidx.compose.ui.geometry.Offset(selectedX, selectedY)
                                    )

                                    // Draw the text for the selected weight
                                    drawContext.canvas.nativeCanvas.apply {
                                        drawText(
                                            selectedWeight.toInt().toString(),
                                            selectedX,
                                            selectedY - 20.dp.toPx(),
                                            android.graphics.Paint().apply {
                                                color = android.graphics.Color.GRAY
                                                textSize = 32f
                                            }
                                        )
                                    }
                                }

                                // Draw the month labels
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    months.forEach { month ->
                                        Text(
                                            text = month,
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp)
                                        )
                                    }
                                }
                            }
                        }

                        // History Card
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .height(100.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = primary
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center // Centrar el contenido del Box
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.materialsymbolshistory),
                                        contentDescription = "History Icon",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .background(
                                                color = weightProgressBackground,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .clip(RoundedCornerShape(15.dp))
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Text(
                                        text = "History",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontSize = 20.sp,
                                        color = white,
                                        modifier = Modifier.padding(all = 16.dp)
                                    )
                                }
                            }
                        }

                        // History cards sera Dinamicos para que sea la cantidad de registros hechos
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HistoryWeightCard(weight, date)
                        }
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HistoryWeightCard(weight, date)
                        }
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HistoryWeightCard(weight, date)
                        }
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HistoryWeightCard(weight, date)
                        }

                    }
                }
            }
        }
    }, navController = navController, getVideoViewModel)
}



