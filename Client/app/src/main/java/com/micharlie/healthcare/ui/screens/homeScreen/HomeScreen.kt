package com.micharlie.healthcare.ui.screens.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.HomeScreenSelector
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.theme.primary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    //Se crea el drawer state para manejar el estado de la barra lateral
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    //Se importa la funcion de drawer bar y se le pasan los parametros, el argumento session
    // state es si esta iniciada la funcion para mostrar los diferentes apartados para la
    // navegaciond e la app, dentro de el drawerBar se crea el scaffold
 DrawerBar(drawerState = drawerState,
     sessionState = true,
     navController = navController,
     content = {
     Scaffold (topBar = {TopBar(drawerState = drawerState) },
         bottomBar = { BottomBar()})
     {
           Column(modifier = Modifier
               .padding(it)
               .fillMaxWidth()
               .fillMaxHeight()
               .background(color = primary))
           {
               HomeScreenContent()


           }

     }
 })
}
@Composable
fun HomeScreenContent(){
    HomeScreenSelector()

}


@Composable
@Preview
fun HomeScreenPreview()
{
    HomeScreen(navController = rememberNavController())
}
