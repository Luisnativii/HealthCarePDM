package com.micharlie.healthcare.ui.components.cards


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.theme.cardsBackgroud
import com.micharlie.healthcare.ui.theme.seeMore
import com.micharlie.healthcare.ui.theme.weightProgress
import com.micharlie.healthcare.ui.theme.weightProgressBackground
import com.micharlie.healthcare.ui.theme.white

// Agregar el Onclick para redirigir a la pantalla de Weight
// Para agregar el onClick colocar esto en el modifier despues de height quedaria:
// .clickable { /* la direccion para ir en este caso seria a la pantalla de Weight */ },

@Composable
fun WeightCard(navController: NavController){
    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(250.dp)
            .clickable { navController.navigate("WeightScreen") },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = cardsBackgroud
        ),
        shape = RoundedCornerShape(10.dp)

    ){

        // Icon and Tittle
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            Image(
                painter = painterResource(id = R.drawable.hugeiconsweightscale__1_),
                contentDescription = "Weight Icon",
                modifier = Modifier
                    .size(40.dp)
                    .background( color = weightProgressBackground, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(15.dp))
            )

            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "Weight",
                    style = MaterialTheme.typography.bodyLarge,
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

        // Weight
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "70", // Aqui debemos de pasar una varible para poder cambiar el valor
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp,
                color = white
            )
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "kg",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    color = white
                )
            }
        }

        // Progress Bar
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                LinearProgressIndicator(
                    progress = 0.5f, // Cambiar esto por el calculo del porcentaje
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp),
                    color = weightProgress,
                    trackColor = weightProgressBackground
                )
            }
        }

        // Low and High
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column {
                Text(
                    text = "low",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    color = white
                )
            }

            Column {
                Text(
                    text = "high",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    color = white
                )
            }
        }
    }
}


@Preview
@Composable
fun WeightCardPreview(){
    val navController = rememberNavController()
    WeightCard(navController = navController)
}
