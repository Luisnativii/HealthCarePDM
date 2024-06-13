package com.micharlie.healthcare.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsDrawer (
    val icon: ImageVector,
    val title: String,
    val route: String,
)

data object RegisterItem: ItemsDrawer(
    icon = Icons.Default.AccountCircle,
    title = "Register",
    route = ""

)
data object LoginItem: ItemsDrawer(
    icon = Icons.Default.AccountBox,
    title = "Login",
    route = ""
)
data object HomeItem: ItemsDrawer(
    icon = Icons.Default.Home,
    title = "Home",
    route = "home"
)
data object HealthItem: ItemsDrawer(
    icon = Icons.Default.Face,
    title = "Salud",
    route = ""
)
data object ContactItem: ItemsDrawer(
    icon = Icons.Default.Email,
    title = "Contacto",
    route = ""
)
data object ExerciseItem: ItemsDrawer(
    icon = Icons.Default.FavoriteBorder,
    title = "Ejercicios",
    route = ""
)
val NoSessionItems = listOf(
    RegisterItem,
    LoginItem
)
val SessionItems = listOf(
    HomeItem,
    HealthItem,
    ContactItem,
    ExerciseItem
)