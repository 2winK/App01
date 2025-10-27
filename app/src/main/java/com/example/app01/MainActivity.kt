package com.example.app01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app01.ui.theme.App01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App01Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel()
) {
    val users by userViewModel.users.collectAsState()
    val error = userViewModel.errorMessage.value

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (error != null) {
            Text(text = error, color = Color.Red, fontSize = 18.sp)
        } else if (users.isEmpty()) {
            Text(text = "Завантаження...")
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(users) { user ->
                    UserListItem(user = user)
                }
            }
        }
    }
}

@Composable
fun UserListItem(user: User, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = user.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = user.email,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserListScreenPreview() {
    App01Theme {
        val testUser = User(1, "Test User", "testuser", "test@example.com")
        UserListItem(user = testUser)
    }
}