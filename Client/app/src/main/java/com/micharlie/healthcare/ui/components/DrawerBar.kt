package com.micharlie.healthcare.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.secondary
import com.micharlie.healthcare.ui.theme.tertiary
import com.micharlie.healthcare.ui.theme.white
import com.micharlie.healthcare.util.ItemsDrawer
import com.micharlie.healthcare.util.NoSessionItems
import com.micharlie.healthcare.util.SessionItems
import kotlinx.coroutines.launch


@Composable
fun DrawerBar( drawerState: DrawerState,sessionState: Boolean, content: @Composable () -> Unit) {
    /*
 * Drawer state maneja el estado de la barra lateral
 * sessioState define si esta cerrado o abierto
 * :Todo: se le agregaria el navCOntroller cuando ya tengamos el navigation implementado
 *   :Todo: la ruta se agrega en el list y agregar el onclick para cambiar de pantalla
 *     :todo: falta extreaer la informacion de usuari, actualmente esta con placeholder
 *   */
    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent =
    {

        ModalDrawerSheet(drawerContainerColor = primary) {
            Column {
                //esta parte muestra la informacion del usuario // si no hay sesion muestra un mensaje
                UserCardDrawer(firstText =
                if(sessionState){"UserName"}
                    else{"Inicia Sesion"},
                    secondText = if(sessionState){"user.email"}
                    else{" o Registrate"}
                )

            }
            // Content of the drawer
            if(sessionState)
            {
                //si hay una sesion iniciada
                SessionItems.forEachIndexed {
                    index, item ->
                    NavigationDrawerItem(
                        label = { Text(item.title) },
                        shape =  RectangleShape,
                        modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp),
                        onClick = {selectedItemIndex = index
                                  scope.launch { drawerState.close() }},
                        selected = index == selectedItemIndex,
                        icon = { Icon(imageVector = item.icon, contentDescription = "$item" ) },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = secondary,
                            selectedContainerColor = tertiary,
                            selectedIconColor = primary,
                            unselectedIconColor = white,
                        )
                    )


                }
            }
            //si no esta iniciada la session
            else
            {
                NoSessionItems.forEachIndexed {
                        index, item ->
                    NavigationDrawerItem(
                        label = { Text(item.title) },
                        shape =  RectangleShape,
                        modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp),
                        onClick = {selectedItemIndex = index
                            scope.launch { drawerState.close() }},
                        selected = index == selectedItemIndex,
                        icon = { Icon(imageVector = item.icon, contentDescription = "$item" ) },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = secondary,
                            selectedContainerColor = tertiary,
                            selectedIconColor = primary,
                            unselectedIconColor = white,
                        )
                    )


                }
            }


        }
    },
        drawerState = drawerState)
    {
        content()
    }
}

@Composable
@Preview
fun DrawerBarPreview()
{
    DrawerBar( rememberDrawerState(initialValue = DrawerValue.Open),true,{})
}