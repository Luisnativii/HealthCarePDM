package com.micharlie.healthcare.ui.screens.ContactScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactScreen(navController: NavController,sessionState: Boolean=true, getVideoViewModel: GetVideoViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    DrawerBar(drawerState = drawerState, sessionState = sessionState , content = {
        Scaffold(
            bottomBar = { BottomBar() },
            topBar = { TopBar(drawerState = drawerState) }
        ) {
            var message by remember { mutableStateOf("") }
            var showMessageSent by remember { mutableStateOf(false) }

            if (showMessageSent) {
                AlertDialog(
                    onDismissRequest = { showMessageSent = false },
                    confirmButton = {
                        TextButton(onClick = { showMessageSent = false }) {
                            Text("OK")
                        }
                    },
                    title = { Text("Message Sent") },
                    text = { Text("Your message has been sent successfully.") }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0A1D48))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Contact ")
                        withStyle(style = SpanStyle(color = Color(0xFF00FF99))) {
                            append("Us")
                        }
                    },
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Let us know what are you thinking",
                    color = Color(0xFFB3C1DD),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Message",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                BasicTextField(
                    value = message,
                    onValueChange = { message = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xFFEAEAEA), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            sendEmail(message)
                            showMessageSent = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFA726),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Send")
                }
            }

        }
    }, navController = navController, getVideoViewModel)
}

fun sendEmail(message: String) {
    val username = "your_email@gmail.com"
    val password = "your_email_password"

    val props = Properties()
    props["mail.smtp.auth"] = "true"
    props["mail.smtp.starttls.enable"] = "true"
    props["mail.smtp.host"] = "smtp.gmail.com"
    props["mail.smtp.port"] = "587"

    val session = Session.getInstance(props,
        object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

    try {
        val mimeMessage = MimeMessage(session)
        mimeMessage.setFrom(InternetAddress(username))
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("healtcare.sv@gmail.com"))
        mimeMessage.subject = "Contact Us Message"
        mimeMessage.setText(message)
        Transport.send(mimeMessage)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}