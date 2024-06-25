package com.micharlie.healthcare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.micharlie.healthcare.data.api.ApiService
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.components.ViewModel.authViewModel
import com.micharlie.healthcare.ui.login.LoginScreen
import com.micharlie.healthcare.ui.login.SharedPreferencesManager
import com.micharlie.healthcare.ui.screens.Community.CreatePostScreen
import com.micharlie.healthcare.ui.screens.ExerciseScreen.ExerciseScreen
import com.micharlie.healthcare.ui.screens.SplashScreen.SplashScreen
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
import com.micharlie.healthcare.ui.signup.RegisterScreen
import com.micharlie.healthcare.utils.Constants
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun Navigation(viewModel: authViewModel) {
    val navController = rememberNavController()
    val retrofit = Retrofit.Builder().baseUrl(Constants.VIDEOBACEURLGET)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory()) // Añade el adaptador de llamadas de corutinas
        .build()
    val sessionState = false
    val apiService =
        retrofit.create(ApiService::class.java) // Replace this with the actual initialization of your ApiService
    val getVideoViewModel = GetVideoViewModel(apiService)
    //val viewmodel: MainViewModel = viewModel()
    val loading =
        remember { mutableStateOf(true) } // Nuevo estado para controlar la pantalla de carga

    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val token = sharedPreferencesManager.getToken()
    val minLoadingTime = 1000L // Tiempo mínimo de carga en milisegundos


    val result = remember { mutableStateOf(false) }

    suspend fun checkToken(token: String?): Boolean {
        if (token != null) {
            getVideoViewModel.getUsersData(token)
            return getVideoViewModel.getUsersDataWithResult(token)
        }
        return false
    }

    LaunchedEffect(key1 = token) {
        val startTime = System.currentTimeMillis()
        result.value = checkToken(token)
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        if (elapsedTime < minLoadingTime) {
            delay(minLoadingTime - elapsedTime) // Asegura que la pantalla de carga se muestra durante al menos minLoadingTime
        }
        loading.value = false // Finaliza la carga una vez que se completa la verificación del token
    }

    if (loading.value) {
        // Muestra la pantalla de carga si la verificación del token está en progreso
        SplashScreen() // Debes crear este composable que representa tu pantalla de carga
    } else {
        // Muestra el NavHost si la verificación del token ha terminado
        NavHost(
            navController = navController,
            startDestination = if (result.value) ScreenRoute.HomeSession.route else ScreenRoute.HomeNoSession.route
        )


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
                HomeScreen(navController, true, getVideoViewModel, sharedPreferencesManager)
            }
            composable(route = ScreenRoute.ExerciseScreen.route) {
                ExerciseScreen(navController, true, getVideoViewModel , sharedPreferencesManager)
            }
            composable(
                route = ScreenRoute.VideoScreen.route + "/{videoUrl}/{name}/{category}",
                arguments = listOf(navArgument("videoUrl") {
                    type = NavType.StringType
                }, navArgument("name") {
                    type = NavType.StringType
                },
                    navArgument("category") {
                        type = NavType.StringType
                    })
            ) {
                val url = it.arguments?.getString("videoUrl") ?: ""
                val name = it.arguments?.getString("name") ?: ""
                val category = it.arguments?.getString("category") ?: ""
                VideoScreen(
                    navController,
                    url,
                    name,
                    true,
                    getVideoViewModel = getVideoViewModel,
                    category = category,
                    sharedPreferencesManager = sharedPreferencesManager
                )
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
                CholesterolScreen(
                    navController = navController,
                    getVideoViewModel = getVideoViewModel
                )
            }
            composable(route = ScreenRoute.HeightScreen.route) {
                HeightScreen(navController = navController, getVideoViewModel = getVideoViewModel)
            }
            composable(route = ScreenRoute.WeightScreen.route) {
                WeightScreen(navController = navController, getVideoViewModel = getVideoViewModel)
            }
            composable(route = ScreenRoute.MuscularMassScreen.route) {
                MuscularMassScreen(
                    navController = navController, getVideoViewModel = getVideoViewModel
                )
            }
            composable(route = ScreenRoute.Login.route) {
                LoginScreen(navController = navController, getVideoViewModel, viewModel)
            }
            composable(route = ScreenRoute.Register.route) {
                RegisterScreen(navController = navController, getVideoViewModel, viewModel, sharedPreferencesManager)
            }
            composable(route = ScreenRoute.HomeSession.route) {
                MainScreen(
                    sessionState = true,
                    getVideoViewModel = getVideoViewModel,
                    navController = navController
                )
            }
            composable(route = ScreenRoute.CommunityScreen.route) {
                CreatePostScreen(navController)
            }
        }
    }
}

