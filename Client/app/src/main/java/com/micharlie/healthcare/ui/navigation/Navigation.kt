package com.micharlie.healthcare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.ui.screens.homeScreen.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    //val viewmodel: MainViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.HomeNoSession.route)
    {
        //Se crean las rutas para cada pantalla y tambien aqui se pasan los argumentos
        /*
        * composable(route = ScreenRoute.Ejemplo.route + "/{argumento}",
            arguments = listOf(navArgument("argumento") {
            * type = NavType.IntType })){
            val Argumento = it.arguments?.getInt("argumento") ?: 0 <- es para que tenga valor
            * predeterminado

            PersonalScreen(viewModel,navController, personList, Argumento)
        }*/
        composable(route = ScreenRoute.HomeNoSession.route) {
            HomeScreen(navController)
        }
        
    }
}