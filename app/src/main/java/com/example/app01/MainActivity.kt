package com.example.app01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app01.ui.theme.App01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App01Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GreetingForm()
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingForm() {
    var name by remember { mutableStateOf("") }
    var greeting by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {

        TextField(
            value = name, // [cite: 155]
            onValueChange = { name = it }, // [cite: 156]
            label = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { greeting = "Hello, $name!" }) {
            Text("Greet me") // [cite: 161]
        }

        Text(greeting)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingFormPreview() {
    App01Theme {
        GreetingForm()
    }
}