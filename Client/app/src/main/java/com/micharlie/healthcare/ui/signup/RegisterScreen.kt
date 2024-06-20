package com.micharlie.healthcare.ui.signup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.data.api.NetworkUtils
import com.micharlie.healthcare.data.api.UserApi
import com.micharlie.healthcare.data.api.UserApiService
import com.micharlie.healthcare.data.model.RegisterRequest
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.navigation.ScreenRoute
import com.micharlie.healthcare.ui.theme.HealthCareTheme
import com.micharlie.healthcare.ui.theme.black
import com.micharlie.healthcare.ui.theme.contrasPrimary
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, getVideoViewModel: GetVideoViewModel) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var dateBirth by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        color = primary,
        modifier = Modifier
            .fillMaxSize()
            .clickable { keyboardController?.hide() }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp), // Ajusta el padding según sea necesario
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.White, fontSize = 30.sp)) {
                                    append("Sign ")
                                }
                                withStyle(style = SpanStyle(color = contrasPrimary,fontSize = 30.sp)) {
                                    append("Up")
                                }
                            }
                            Text(text, style = MaterialTheme.typography.titleLarge)
                            ClickableText(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append("You have an account already? Click here")
                                    }
                                },
                                onClick = { offset ->
                                    navController.navigate(ScreenRoute.Login.route) // Asegúrate de que este es el nombre correcto de la ruta de la pantalla de inicio de sesión
                                },
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            containerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
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
                        modifier = Modifier.fillMaxWidth()
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
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = gender,
                        onValueChange = { gender = it },
                        label = { Text("Gender") },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            containerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = dateBirth,
                        onValueChange = { dateBirth = it },
                        label = { Text("Date of Birth (YYYY-MM-DD)") },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            containerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (errorMessage.isNotEmpty()) {
                        Text(errorMessage, color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(
                        onClick = {

                            navController.navigate(ScreenRoute.HomeSession.route)

                            val user = UserApi(
                                name = name,
                                email = email,
                                gender = gender,
                                dateBirth = dateBirth,
                                password = password
                            )

                            val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
                            val service = retrofit.create(UserApiService::class.java)
                            val call = service.postUser(user)

                            call.enqueue(object : Callback<String> {
                                override fun onResponse(call: Call<String>, response: Response<String>) {
                                    try {
                                        if (response.isSuccessful) {
                                            val token = response.body()
                                            println("Post successful, token: $token")
                                            //por fines educativos se comento para no iniciar secion para los qu eno tiene la bace
                                            //navController.navigate(ScreenRoute.HomeSession.route)

                                        } else {
                                            println("Post failed: ${response.errorBody()?.string()}")
                                            println(errorMessage)
                                        }
                                    } catch (e: Exception) {
                                        println("Error parsing response: ${e.message}")
                                        println(errorMessage)
                                    }
                                }

                                override fun onFailure(call: Call<String>, t: Throwable) {
                                    println("Post failed: ${t.message}")
                                    println(errorMessage)
                                }
                            })
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = contrasPrimary,
                            contentColor = black
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Register")
                    }
                }
            }
        }
    }
}

