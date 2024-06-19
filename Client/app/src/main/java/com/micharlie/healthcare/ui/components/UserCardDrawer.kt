package com.micharlie.healthcare.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.white

@Composable
fun UserCardDrawer(firstText: String, secondText: String)
{
    //Card para mostrar el usuario
    Card(modifier = Modifier.fillMaxWidth().height(100.dp),
        colors = CardDefaults.cardColors(containerColor = primary),
    )
    {
       Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ) {
           //se usan las vcariables en los argumentos para mostrar el nombre y el correo del usuario

              Text(text = firstText,
                  modifier = Modifier.padding(5.dp),
                  color = white,
                  fontSize = 25.sp,
                  fontWeight = FontWeight.Bold
              )
              Text(text = secondText,
                  color = white,
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Light)
       }


    }
}

@Composable
@Preview
fun UserCardDrawerPreview()
{
    UserCardDrawer("Prueba", "Prueba2")
}