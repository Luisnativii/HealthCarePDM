package com.micharlie.healthcare.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micharlie.healthcare.ui.theme.HealthCareTheme
import com.micharlie.healthcare.ui.theme.black
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.data.api.NetworkUtils
import com.micharlie.healthcare.data.api.UserApi
import com.micharlie.healthcare.data.api.UserApiService
import com.micharlie.healthcare.data.api.dataApi
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.components.ViewModel.authViewModel
import com.micharlie.healthcare.ui.navigation.ScreenRoute
import com.micharlie.healthcare.ui.theme.contrasPrimary
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.utils.Constants
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, getVideoViewModel: GetVideoViewModel, viewModel: authViewModel) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        color = primary,
        modifier = Modifier
            .fillMaxSize()
            .clickable { keyboardController?.hide() }
        ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White,fontSize = 30.sp)) {
                        append("Log ")
                    }
                    withStyle(style = SpanStyle(color = contrasPrimary,fontSize = 30.sp)) {
                        append("In")
                    }
                }
                Text(text, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))  // Espaciador entre los textos
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("First time here? Click here")
                        }
                    },
                    onClick = { offset ->
                        navController.navigate(ScreenRoute.Register.route) // Asegúrate de que este es el nombre correcto de la ruta de la pantalla de registro
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(32.dp))  // Espaciador antes de los campos de texto

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        containerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        containerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (errorMessage.isNotEmpty()) {
                    Text(errorMessage, color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Button(
                    onClick = {
                        navController.navigate(ScreenRoute.HomeSession.route)
                        val login = UserApi(
                            email = email,
                            password = password
                        )

                        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
                        val service = retrofit.create(UserApiService::class.java)
                        val call = service.loginUser(login)

                        call.enqueue(object : Callback<String> {
                            override fun onResponse(call: retrofit2.Call<String>, response: Response<String>) {
                                try {
                                    if (response.isSuccessful) {
                                        val sharedPreferencesManager = SharedPreferencesManager(context)
                                        val token = response.body()
                                        val t = token ?: "$token"
                                        sharedPreferencesManager.saveToken(t)
                                        println("Login successful: $token")

                                        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
                                        val service = retrofit.create(UserApiService::class.java)

                                        viewModel.traerToken()
                                        val x = viewModel.token
                                        println("Token data Store: $x")

                                        // Hacer la llamada GET para obtener todos los datos de los usuarios
                                        //usando data Store en X
                                        //Sin usar data Store T

                                        val callGetUsers = service.getUsers("Bearer $t")
                                        println("Bearer $x")
                                        println("Token data Store: ${viewModel.token.value}")

                                        getVideoViewModel.getUsersData(t)

                                        //navController.navigate(ScreenRoute.HomeSession.route)
                                    } else {
                                        println("Login failed: ${response.errorBody()?.string()}")
                                    }
                                } catch (e: Exception) {
                                    println("Error parsing response: ${e.message}")
                                }
                            }

                            override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                                println("Login failed: ${t.message}")
                            }
                        })
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = contrasPrimary,
                        contentColor = black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                ) {
                    Text("Login")
                }
            }
        }
    }
}