package com.micharlie.healthcare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.micharlie.healthcare.data.api.ApiService
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.screens.ExerciseScreen.ExerciseScreen
import com.micharlie.healthcare.ui.screens.VideoScreen.VideoScreen
import com.micharlie.healthcare.ui.screens.bloodGlucose.BloodGlucoseScreen
import com.micharlie.healthcare.ui.screens.bloodPressure.BloodPressureScreen
import com.micharlie.healthcare.ui.screens.bodyFat.BodyFatScreen
import com.micharlie.healthcare.ui.screens.cholesterolScreen.CholesterolScreen
import com.micharlie.healthcare.ui.screens.heightScreen.HeightScreen
import com.micharlie.healthcare.ui.screens.homeScreen.HomeScreen
import com.micharlie.healthcare.ui.screens.mainScreen.MainScreen
import com.micharlie.healthcare.ui.screens.muscularMass.MuscularMassScreen
import com.micharlie.healthcare.ui.screens.weightScreen.WeightScreen
import com.micharlie.healthcare.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun Navigation(sessionState: Boolean = true) {
    val navController = rememberNavController()
    val retrofit = Retrofit.Builder().baseUrl(Constants.VIDEOBACEURLGET)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory()) // Añade el adaptador de llamadas de corutinas
        .build()

    val apiService =
        retrofit.create(ApiService::class.java) // Replace this with the actual initialization of your ApiService
    val getVideoViewModel = GetVideoViewModel(apiService)
    //val viewmodel: MainViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = if (sessionState) ScreenRoute.HomeSession.route else ScreenRoute.HomeNoSession.route
    ) {
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
            HomeScreen(navController, true, getVideoViewModel)
        }
        composable(route = ScreenRoute.ExerciseScreen.route) {
            ExerciseScreen(navController, true, getVideoViewModel)
        }
        composable(
            route = ScreenRoute.VideoScreen.route + "/{url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
            })
        ) {
            val url = it.arguments?.getString("url") ?: ""
            VideoScreen(navController, url, true, getVideoViewModel)
        }
        composable(route = ScreenRoute.HomeSession.route) {
            MainScreen(
                getVideoViewModel = getVideoViewModel,
                navController = navController,
                sessionState = true
            )
        }
        composable(route = ScreenRoute.BloodGlucoseScreen.route) {
            BloodGlucoseScreen(
                navController = navController, getVideoViewModel = getVideoViewModel
            )
        }
        composable(route = ScreenRoute.BloodPressureScreen.route) {
            BloodPressureScreen(
                navController = navController, getVideoViewModel = getVideoViewModel
            )
        }
        composable(route = ScreenRoute.BodyFatScreen.route) {
            BodyFatScreen(
                navController = navController, getVideoViewModel = getVideoViewModel
            )
        }
        composable(route = ScreenRoute.CholesterolScreen.route) {
            CholesterolScreen(navController = navController, getVideoViewModel = getVideoViewModel)
        }
        composable(route = ScreenRoute.HeightScreen.route) {
            HeightScreen(navController = navController, getVideoViewModel =getVideoViewModel )
        }
        composable (route = ScreenRoute.WeightScreen.route){
            WeightScreen(navController = navController, getVideoViewModel = getVideoViewModel)
        }
        composable (route = ScreenRoute.MuscularMassScreen.route){
            MuscularMassScreen(navController = navController, getVideoViewModel = getVideoViewModel)
        }

    }
}

