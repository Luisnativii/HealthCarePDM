package com.micharlie.healthcare.ui.screens.bloodPressure
import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.components.historyCards.HistoryBloodPressureCard
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.theme.PressurecolorBackground
import com.micharlie.healthcare.ui.theme.bloodPressureColor
import com.micharlie.healthcare.ui.theme.cardsBackgroud
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BloodPressureScreen(
    navController: NavController,
    sessionState: Boolean = true,
    getVideoViewModel: GetVideoViewModel,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var bloodPressureSystolic by remember {
        mutableIntStateOf(110)//cambiar por vm
    }
    var bloodPressureDiastolic by remember {
        mutableIntStateOf(75)//cambiar por vm
    }
    var date by remember {
        mutableStateOf("2021-10-10")//cambiar por vm
    }


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
                        // Card Icon and Tittle Blood Pressure
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
                                    painter = painterResource(id = R.drawable.materialsymbolsbloodpressureoutline),
                                    contentDescription = "Blood Pressure Icon",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .background(
                                            color = PressurecolorBackground,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .clip(RoundedCornerShape(15.dp))
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = "Blood Pressure",
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
                        var bloodPressureSystolicInput by remember {
                            mutableStateOf(
                                bloodPressureSystolic.toString()
                            )
                        }
                        var bloodPressureDiastolicInput by remember {
                            mutableStateOf(
                                bloodPressureDiastolic.toString()
                            )
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .height(250.dp),
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
                                        value = bloodPressureSystolicInput, // Mandando a llamar la BloodGlucose
                                        onValueChange = { bloodPressureSystolicInput = it },
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                                    )
                                    Box(
                                        modifier = Modifier.padding(16.dp),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Systolic", // Cambiar por una variable de usuario o db
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = white
                                            )
                                            Text(
                                                text = "mmHg",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = Color.Gray
                                            )
                                        }
                                    }

                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    TextField(
                                        value = bloodPressureDiastolicInput, // Nueva variable para diastolic input
                                        onValueChange = { bloodPressureDiastolicInput = it },
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                                    )
                                    Box(
                                        modifier = Modifier.padding(16.dp),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Diastolic", // Cambiar por una variable de usuario o db
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = white
                                            )
                                            Text(
                                                text = "mmHg",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = Color.Gray
                                            )
                                        }
                                    }
                                }


                                Button(
                                    onClick = { /* Handle update logic */ },
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
                                    .width(100.dp)
                                    .height(200.dp)
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
                                            text = "110", // Cambiar por una variable de usuario o db
                                            fontSize = 25.sp,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = white
                                        )
                                        Text(
                                            text = "75",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = white
                                        )
                                        Text(
                                            text = "mmHg",
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
                                    .height(200.dp)
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
                                            progress = 0.7f,
                                            color = bloodPressureColor,
                                            trackColor = PressurecolorBackground,
                                            modifier = Modifier
                                                .height(16.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )

                                        Spacer(modifier = Modifier.height(16.dp))

                                        Text(
                                            text = "Normal", // Se tiene que validar para ver que le vamos a poner
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = white
                                        )

                                        LinearProgressIndicator(
                                            progress = 0.5f,
                                            color = bloodPressureColor,
                                            trackColor = PressurecolorBackground,
                                            modifier = Modifier
                                                .height(16.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                    }
                                }
                            }
                        }

                        // Graphics Card
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .height(150.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = cardsBackgroud
                            )
                        ) {

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
                                        painter = painterResource(id = R.drawable.materialsymbolshistory__3_),
                                        contentDescription = "History Icon",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .background(
                                                color = PressurecolorBackground,
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

                        // History Cards
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HistoryBloodPressureCard(
                                bloodPressureSystolic, bloodPressureDiastolic, date
                            )
                        }
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HistoryBloodPressureCard(
                                bloodPressureSystolic, bloodPressureDiastolic, date
                            )
                        }
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HistoryBloodPressureCard(
                                bloodPressureSystolic, bloodPressureDiastolic, date
                            )
                        }
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HistoryBloodPressureCard(
                                bloodPressureSystolic, bloodPressureDiastolic, date
                            )
                        }

                    }
                }
            }
        }
    }, navController = navController, getVideoViewModel)
}
