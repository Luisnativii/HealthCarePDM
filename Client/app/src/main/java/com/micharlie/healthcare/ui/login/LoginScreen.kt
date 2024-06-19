package com.micharlie.healthcare.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.micharlie.healthcare.ui.theme.HealthCareTheme
import com.micharlie.healthcare.ui.theme.black
import androidx.lifecycle.viewmodel.compose.viewModel
import com.micharlie.healthcare.ui.theme.contrasPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor =MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor =MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { loginViewModel.login(email, password) },
            colors = ButtonDefaults.buttonColors(
                containerColor = contrasPrimary,
                contentColor = black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    HealthCareTheme {
        LoginScreen()
    }
}
