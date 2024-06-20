package com.micharlie.healthcare.ui.historyCards

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
import com.micharlie.healthcare.ui.theme.cardsBackgroud
import com.micharlie.healthcare.ui.theme.cholesterolProgressBackground
import com.micharlie.healthcare.ui.theme.weightProgressBackground

@Composable
fun HistoryCholesterolCard(){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(100.dp),
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
                    painter = painterResource(id = R.drawable.healthiconsgallbladderoutline),
                    contentDescription = "Cholesterol Icon",
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = cholesterolProgressBackground, shape = RoundedCornerShape(10.dp))
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
                        Text(
                            text = "90",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 25.sp,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "mg/dL",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                    Text(
                        text = "12/12/2024", // Date
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
fun HistoryCholesterolCardPreview(){
    HistoryCholesterolCard()
}