package com.micharlie.healthcare.ui.signup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.micharlie.healthcare.data.model.RegisterRequest

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var physical by remember { mutableStateOf("") }

    // UI para registro
    /*LazyColumn {
        item {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") }
                )
                TextField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = { Text("Gender") }
                )
                TextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") }
                )
                TextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Height") }
                )
                TextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight") }
                )
                TextField(
                    value = physical,
                    onValueChange = { physical = it },
                    label = { Text("Physical") }
                )
                Button(onClick = {
                    val user = RegisterRequest(name, email, password, gender, age, height, weight, physical)
                    registerViewModel.register(user)
                }) {
                    Text("Register")
                }
            }
        }
    }*/
}