package com.micharlie.healthcare.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.theme.PressurecolorBackground
import com.micharlie.healthcare.ui.theme.cardsBackgroud
import com.micharlie.healthcare.ui.theme.seeMore
import com.micharlie.healthcare.ui.theme.white

// Agregar el Onclick para redirigir a la pantalla de Blood Pressure
// Para agregar el onClick colocar esto en el modifier despues de height quedaria:
// .clickable { /* la direccion para ir en este caso seria a la pantalla de BloodPressure () */ },
@Composable
fun BloodPressureCard(navController: NavHostController) {
    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(200.dp)
            .clickable { navController.navigate("BloodPressureScreen") },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = cardsBackgroud
        ),
        shape = RoundedCornerShape(10.dp)

    ){
        // Icon and Tittle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.materialsymbolsbloodpressureoutline),
                contentDescription = "Blood Pressure Icon",
                modifier = Modifier
                    .size(40.dp)
                    .background( color = PressurecolorBackground, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(15.dp))
            )

            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "Blood Pressure",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    color = white
                )
                Text(
                    text = "See more",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    color = seeMore
                )
            }
        }

        // Blood Pressure Values
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .padding(start = 56.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text ="110", // Cambiar esto por lo que debe de recibir de la base de datos o del usuario
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 30.sp,
                    color = Color.White
                )
                Text(
                    text ="74", // Cambiar esto por lo que debe de recibir de la base de datos o del usuario
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 28.dp)
                )
            }
            Text(
                text = "mmHg",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 15.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

    }
}


@Preview
@Composable
fun BloodPressureCardPreview() {
    val navController = rememberNavController()
    BloodPressureCard(navController = navController)
}