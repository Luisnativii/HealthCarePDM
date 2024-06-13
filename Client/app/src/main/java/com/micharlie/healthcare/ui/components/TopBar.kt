package com.micharlie.healthcare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.micharlie.healthcare.R
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        //agregando al titulo la imagen de la aplicacion
        title = { Image(painter = painterResource(R.drawable.logo_blue), contentDescription
    = "logo", contentScale = ContentScale.FillBounds) },
        //cambiando los colores de la barra
        colors
    = TopAppBarDefaults
        .topAppBarColors
        (containerColor = primary), actions = {IconButton(onClick = { /*TODO*/ },
            //agregando el contenido del topbar
        content = {
            //boton para desplegar el menu
        IconButton(
            onClick = { /*TODO*/ },
            content = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = white
                )
            }
    )
        })})
}



@Composable
@Preview
fun TopBarPreview()
{
    TopBar()
}
