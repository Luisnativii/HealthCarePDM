package com.micharlie.healthcare.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.micharlie.healthcare.ui.theme.*
import kotlinx.coroutines.launch
@Composable
fun UserProfileScreen(userProfileViewModel: UserProfileViewModel = viewModel()) {
    val userProfile = userProfileViewModel.userProfile.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        userProfileViewModel.loadUserProfile()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Health Care", style = MaterialTheme.typography.headlineMedium, color = white)

        Text("Your data", style = MaterialTheme.typography.titleMedium, color = secondary)

        userProfile.value?.let { profile ->
            ProfileItem(label = "Name", value = profile.name)
            ProfileItem(label = "Change password", value = "******") {
                // Aquí puedes manejar la acción de cambiar contraseña
            }
            ProfileItem(label = "Log out") {
                // Aquí puedes manejar la acción de cerrar sesión
            }
            ProfileItem(label = "Delete my data") {
                // Aquí puedes manejar la acción de eliminar datos
            }
            ProfileItem(label = "Delete my account") {
                // Aquí puedes manejar la acción de eliminar cuenta
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text("Health care", style = MaterialTheme.typography.bodySmall, color = tertiary)
        Text("© SoftDevelop. All Rights Reserved.", style = MaterialTheme.typography.bodySmall, color = tertiary)
    }
}

@Composable
fun ProfileItem(label: String, value: String? = null, onClick: (() -> Unit)? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(secondary)
            .padding(8.dp)
    ) {
        if (value != null) {
            Text("$label: $value", style = MaterialTheme.typography.bodyLarge, color = white)
        } else {
            Button(
                onClick = { onClick?.invoke() },
                colors = ButtonDefaults.buttonColors(containerColor = contrasPrimary, contentColor = black)
            ) {
                Text(label)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen()
}