package com.micharlie.healthcare.ui.navigation

sealed class ScreenRoute(var route: String){
//data object *nombre* : ScreenRoute("ruta")
    data object HomeNoSession : ScreenRoute("homeNoSession")
}