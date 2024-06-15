package com.micharlie.healthcare.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Login
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material.icons.rounded.HealthAndSafety
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Login
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.micharlie.healthcare.R

sealed class ItemsDrawer (
    val icon: ImageVector,
    val title: String,
    val route: String,
)

data object RegisterItem: ItemsDrawer(
    icon = Icons.Rounded.AccountCircle,
    title = "Register",
    route = ""

)
data object LoginItem: ItemsDrawer(
    icon = Icons.AutoMirrored.Rounded.Login,
    title = "Login",
    route = ""
)
data object HomeItem: ItemsDrawer(
    icon = Icons.Rounded.Home,
    title = "Home",
    route = "home"
)
data object HealthItem: ItemsDrawer(

    icon = Icons.Rounded.HealthAndSafety,
    title = "Salud",
    route = ""
)
data object ContactItem: ItemsDrawer(
    icon = Icons.Rounded.SupportAgent,
    title = "Contacto",
    route = ""
)
data object ExerciseItem: ItemsDrawer(
    icon = Icons.Rounded.FitnessCenter,
    title = "Ejercicios",
    route = ""
)
data object LogOutItem: ItemsDrawer(
    icon = Icons.AutoMirrored.Rounded.Logout,
    title = "Log Out",
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
    ExerciseItem,
    LogOutItem
)