package com.micharlie.healthcare.ui.screens.heightScreen

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.components.historyCards.HistoryHeightCard
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.login.SharedPreferencesManager
import com.micharlie.healthcare.ui.theme.cardsBackgroud
import com.micharlie.healthcare.ui.theme.contrast1
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.heightBackground
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.white
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HeightScreen(
    navController: NavController, getVideoViewModel: GetVideoViewModel, sessionState: Boolean = true
) {

    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val token = sharedPreferencesManager.getToken()

    if (token != null) {
        LaunchedEffect(key1 = token) {
            while (true) {
                getVideoViewModel.getUsersData(token)
                delay(5000) // Actualiza cada 5 segundos
            }
        }
    }

    val userData by getVideoViewModel.userData.observeAsState(initial = emptyList())

    val heightDateList = mutableListOf<Pair<Int, String>>()
    for (user in userData) {
        val height = user.height?.toIntOrNull()
        val date = user.date ?: "No date" // Usa "No date" si user.date es null
        if (height != null && height != 0) {
            heightDateList.add(Pair(height, date))
        }
    }
    println("HeightDateList: $heightDateList")



    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var height by remember {
        mutableIntStateOf(190)
    }
    var date by remember {
        mutableStateOf("2021-10-10")
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
                        // Card Icon and Tittle Height
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
                                    painter = painterResource(id = R.drawable.materialsymbolsheight),
                                    contentDescription = "Height Icon",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .background(
                                            color = heightBackground,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .clip(RoundedCornerShape(15.dp))
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = "Height",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontSize = 20.sp,
                                    color = white,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(all = 16.dp)
                                )
                            }
                        }


                        // Card input Height
                        var heightInput by remember { mutableStateOf("170") }
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
                                        value = heightInput, // Cambiar por lo que ponga el usuario

                                        onValueChange = { heightInput = it },

                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                                    )
                                    Text(
                                        text = "cm",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontSize = 16.sp,
                                        color = white,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = onClick@{

                                        val heightInt = heightInput.toIntOrNull() ?: return@onClick
                                        val token = sharedPreferencesManager.getToken()
                                        if (token != null) {
                                            getVideoViewModel.updateHeight(token, heightInt)
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

                        // Card cm
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .height(75.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = cardsBackgroud
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "170",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontSize = 20.sp,
                                        color = white,
                                        modifier = Modifier.padding(end = 4.dp)
                                    )

                                    Text(
                                        text = "cm",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontSize = 16.sp,
                                        color = contrast1,
                                        modifier = Modifier.padding(start = 4.dp)
                                    )
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
                                        painter = painterResource(id = R.drawable.materialsymbolshistory__1_),
                                        contentDescription = "History Icon",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .background(
                                                color = heightBackground,
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

                        // History Cards despues se cambiara por una lista de cosas que traera de la API



                    }
                    items(heightDateList) { (height, date) ->
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HistoryHeightCard(height, date)
                        }
                    }
                }
            }
        }
    }, navController = navController, getVideoViewModel , sharedPreferencesManager)
}
