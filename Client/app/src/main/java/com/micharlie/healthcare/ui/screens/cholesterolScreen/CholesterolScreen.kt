package com.micharlie.healthcare.ui.screens.cholesterolScreen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.components.historyCards.HistoryCholesterolCard
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.theme.cardsBackgroud
import com.micharlie.healthcare.ui.theme.cholesterolProgress
import com.micharlie.healthcare.ui.theme.cholesterolProgressBackground
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CholesterolScreen(
    navController: NavController,
    sessionState: Boolean = true,
    getVideoViewModel: GetVideoViewModel,
    cholesterol: Int,
    date: String
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    DrawerBar(drawerState = drawerState, sessionState = true , content = {
        Scaffold(
            bottomBar = { BottomBar() },
            topBar = { TopBar(drawerState = drawerState) }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .background(primary)
            ){
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
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    painter = painterResource(id = R.drawable.healthiconsgallbladderoutline),
                                    contentDescription = "Cholesterol Icon",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .background(
                                            color = cholesterolProgressBackground,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .clip(RoundedCornerShape(15.dp))
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = "Cholesterol",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontSize = 20.sp,
                                    color = white,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(all = 16.dp)
                                )
                            }
                        }


                        // Card input Cholesterol
                        var cholesterolInput by remember { mutableStateOf("90") }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .height(150.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = cardsBackgroud
                            )
                        ){
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ){
                                    TextField(
                                        value = cholesterolInput , // Mandando a llamar el peso
                                        onValueChange = { cholesterolInput = it },
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier
                                            .size(width = 150.dp, height = 50.dp)
                                    )
                                    Text(
                                        text = "mg/dL",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontSize = 16.sp,
                                        color = white,
                                        modifier = Modifier
                                            .padding(16.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

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
                        ){
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
                                    Column (
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ){
                                        Text(
                                            text = "90", // Cambiar por una variable de usuario o db
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = white
                                        )
                                        Text(
                                            text = "mg/dL",
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
                                    Column (
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ){
                                        Text(
                                            text = "Normal", // Se tiene que validar para ver que le vamos a poner
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = white
                                        )

                                        Spacer(modifier = Modifier.height(8.dp))

                                        LinearProgressIndicator(
                                            progress = 0.5f,
                                            color = cholesterolProgress,
                                            trackColor = cholesterolProgressBackground,
                                            modifier = Modifier
                                                .height(16.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                    }
                                }
                            }
                        }

                        // Graphics Card
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .height(150.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = cardsBackgroud
                            )
                        ){

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
                                modifier = Modifier
                                    .fillMaxSize(),
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
                                        painter = painterResource(id = R.drawable.materialsymbolshistory__4_),
                                        contentDescription = "History Icon",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .background(
                                                color = cholesterolProgressBackground,
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
                                        modifier = Modifier
                                            .padding(all = 16.dp)
                                    )
                                }
                            }
                        }

                        // History Cards luego se cambiara por una lista de la Api
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                        ){
                            HistoryCholesterolCard(cholesterol, date)
                        }
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                        ){
                            HistoryCholesterolCard(cholesterol, date)
                        }
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                        ){
                            HistoryCholesterolCard(cholesterol, date)
                        }
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                        ){
                            HistoryCholesterolCard(cholesterol, date)
                        }
                    }
                }
            }
        }
    }, navController = navController, getVideoViewModel)
}
