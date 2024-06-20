package com.micharlie.healthcare.ui.components.cards

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.theme.cardsBackgroud
import com.micharlie.healthcare.ui.theme.heightBackground
import com.micharlie.healthcare.ui.theme.seeMore


// Agregar el Onclick para redirigir a la pantalla de Height
// Para agregar el onClick colocar esto en el modifier despues de height
// .clickable { /* la direccion para ir en este caso seria a la pantalla de Height */ }
@Composable
fun HeightCard(navController: NavController, height: Int){
    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(150.dp)
            .clickable { navController.navigate("HeightScreen") },
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
                painter = painterResource(id = R.drawable.materialsymbolsheight),
                contentDescription = "Weight Icon",
                modifier = Modifier
                    .size(40.dp)
                    .background(color = heightBackground, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(15.dp))
            )

            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "Height",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = Color.White
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
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = height.toString(), // Aquí debemos de pasar una variable para cambiar el valor
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp,
                color = Color.White
            )
            Text(
                text = " kg",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}


@Preview
@Composable
fun HeightPreview(){
    val navController = rememberNavController()
    HeightCard(navController = navController, height = 70)
}
