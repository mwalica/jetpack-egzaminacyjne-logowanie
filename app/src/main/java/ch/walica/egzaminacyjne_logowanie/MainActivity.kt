package ch.walica.egzaminacyjne_logowanie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ch.walica.egzaminacyjne_logowanie.ui.theme.Egzaminacyjne_logowanieTheme
import ch.walica.egzaminacyjne_logowanie.ui.theme.Teal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Egzaminacyjne_logowanieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val enteredEmail = remember {
        mutableStateOf("")
    }

    val enteredPassword1 = remember {
        mutableStateOf("")
    }

    val enteredPassword2 = remember {
        mutableStateOf("")
    }

    val result = remember {
        mutableStateOf("Autor: 00000000")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "logonAppAs") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF7E57C2),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rejestruj konto",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Teal),
                fontSize = 26.sp,
                color = Color.White
            )
            Text(text = "Podaj email:", modifier = Modifier.fillMaxWidth())
            TextField(
                value = enteredEmail.value,
                onValueChange = { text -> enteredEmail.value = text },
                placeholder = { Text(text = "email") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Text(text = "Podaj hasło:", modifier = Modifier.fillMaxWidth())
            TextField(
                value = enteredPassword1.value,
                onValueChange = { text -> enteredPassword1.value = text},
                placeholder = { Text(text = "podaj hasło") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Text(text = "Powtórz hasło:", modifier = Modifier.fillMaxWidth())
            TextField(
                value = enteredPassword2.value,
                onValueChange = { text -> enteredPassword2.value = text},
                placeholder = { Text(text = "powtórz hasło") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                if (!enteredEmail.value.contains("@")) {
                    result.value = "Nieprawidłowy adres e-mail"
                    return@Button
                }

                if (enteredPassword1.value != enteredPassword2.value) {
                    result.value = "Hasła się różnią"
                    return@Button
                }

                result.value = "Witaj ${enteredEmail.value}"
            }) {
                Text(text = "zatwierdź".uppercase())
            }
            Text(text = result.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Egzaminacyjne_logowanieTheme {
        MainScreen()
    }
}