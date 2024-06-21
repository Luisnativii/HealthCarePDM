package com.micharlie.healthcare.ui.navigation
sealed class ScreenRoute(var route: String) {
    //data object *nombre* : ScreenRoute("ruta")
    data object HomeNoSession : ScreenRoute("homeNoSession")
    data object ExerciseScreen : ScreenRoute("exerciseScreen")
    data object VideoScreen : ScreenRoute("videoScreen")
    data object HomeSession : ScreenRoute("homeSession")
    data object BloodGlucoseScreen : ScreenRoute("bloodGlucoseScreen")
    data object BloodPressureScreen : ScreenRoute("bloodPressureScreen")
    data object BodyFatScreen : ScreenRoute("bodyFatScreen")
    data object CholesterolScreen : ScreenRoute("cholesterolScreen")
    data object MuscularMassScreen : ScreenRoute("muscularMassScreen")
    data object WeightScreen : ScreenRoute("weightScreen")
    data object HeightScreen : ScreenRoute("heightScreen")
    data object Register: ScreenRoute("register")
    data object Login: ScreenRoute("login")
    data object FoodPlan: ScreenRoute("foodPlan")
    data object RecipeList: ScreenRoute("recipeList")
}