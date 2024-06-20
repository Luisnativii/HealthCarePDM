package com.micharlie.healthcare.ui.components.historyCards

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.micharlie.healthcare.ui.theme.PressurecolorBackground
import com.micharlie.healthcare.ui.theme.cardsBackgroud


@Composable
fun HistoryBloodPressureCard(bloodPressureSystolic:Int , bloodPressureDiastolic:Int, date:String){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(120.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = cardsBackgroud
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
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center // Centrar el contenido de la Row
            ) {
                Image(
                    painter = painterResource(id = R.drawable.materialsymbolsbloodpressureoutline),
                    contentDescription = "Blood Pressure Icon",
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = PressurecolorBackground,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(15.dp))
                )

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, // Centrar horizontalmente
                    verticalArrangement = Arrangement.Center // Centrar verticalmente
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center // Centrar horizontalmente
                    ) {

                        Column (
                            horizontalAlignment = Alignment.End
                        ){
                            Text(
                                text = bloodPressureSystolic.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = 23.sp,
                                color = Color.White
                            )
                            Text(
                                text = bloodPressureDiastolic.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = 15.sp,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "mmHg",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                    Text(
                        text = date.toString(), // Date
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HistoryBloodPressureCardPreview(){
    HistoryBloodPressureCard(bloodPressureSystolic = 110, bloodPressureDiastolic = 70, date = "09/10/2024")
}