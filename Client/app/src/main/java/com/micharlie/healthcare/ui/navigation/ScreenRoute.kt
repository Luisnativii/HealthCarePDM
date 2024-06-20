package com.micharlie.healthcare.ui.navigation

sealed class ScreenRoute(var route: String){
//data object *nombre* : ScreenRoute("ruta")
    data object HomeNoSession : ScreenRoute("homeNoSession")
    data object ExerciseScreen: ScreenRoute("exerciseScreen")
    data object VideoScreen: ScreenRoute("videoScreen")
    data object HomeSession: ScreenRoute("homeSession")
}