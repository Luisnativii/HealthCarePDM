package com.micharlie.healthcare.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.theme.bodyFatProgress
import com.micharlie.healthcare.ui.theme.bodyFatProgressBackground
import com.micharlie.healthcare.ui.theme.cardsBackgroud
import com.micharlie.healthcare.ui.theme.seeMore
import com.micharlie.healthcare.ui.theme.weightProgressBackground
import com.micharlie.healthcare.ui.theme.white

// Agregar el Onclick para redirigir a la pantalla de Body Fat
// Para agregar el onClick colocar esto en el modifier despues de height
// .clickable { /* la direccion para ir en este caso seria a la pantalla de Body Fat */ }
@Composable
fun BodyFat() {
    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(250.dp),
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
                painter = painterResource(id = R.drawable.materialsymbolsbodyfat),
                contentDescription = "Body Fat Icon",
                modifier = Modifier
                    .size(40.dp)
                    .background( color = bodyFatProgressBackground, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(15.dp))
            )

            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "Body Fat",
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

        // Body Fat
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "10", // Cambiar esto por lo que se debe recibir de la base de datos o del usuario
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
                    text = "%",
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
                    progress = 0.5f, // Cambiar esto por lo que se debe recibir de la base de datos o del usuario para el porcentaje
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp),
                    color = bodyFatProgress,
                    trackColor = bodyFatProgressBackground
                )
            }
        }

        // Low and High
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column {
                Text(
                    text = "Low",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    color = white
                )
            }

            Column {
                Text(
                    text = "High",
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
fun BodyFatPreview() {
    BodyFat()
}