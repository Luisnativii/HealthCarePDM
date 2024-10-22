package com.micharlie.healthcare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.white
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        //agregando al titulo la imagen de la aplicacion
        title = {
            Image(
                painter = painterResource(R.drawable.logo_blue), contentDescription
                = "logo", contentScale = ContentScale.FillBounds
            )
        }, navigationIcon = {
            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = "", tint = white)

            }
        },
        //cambiando los colores de la barra
        colors
        = TopAppBarDefaults
            .topAppBarColors
                (containerColor = primary), actions = {

        })
}


@Composable
@Preview
fun TopBarPreview() {
    TopBar(drawerState = rememberDrawerState(initialValue = DrawerValue.Closed))
}
