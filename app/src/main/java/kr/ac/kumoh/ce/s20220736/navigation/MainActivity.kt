package kr.ac.kumoh.ce.s20220736.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import kr.ac.kumoh.ce.s20220736.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost (
            navController = navController,
            startDestination = "nav_screen1",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("screen1") {
                FirstScreen(navController)
            }
            composable("screen2") {
                SecondScreen(navController)
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    Column {
        Text("화면 1")
        Button(
            onClick = {
                navController.navigate("screen2") {
                    // 이미 화면이 스택에 있다면 새로 만들지 않고 기존 화면으로 이동
                    launchSingleTop = true
                    popUpTo("screen2") { inclusive = true }
                }
            }
        ) {
            Text("화면 2로 이동")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    Column {
        Text("화면 2")
        Button(
            onClick = {
                navController.navigate("screen1") {
                    // 이미 화면이 스택에 있다면 새로 만들지 않고 기존 화면으로 이동
                    launchSingleTop = true
                    popUpTo("screen1") { inclusive = true }
                }
            }
        ) {
            Text("화면 1로 이동")
        }
    }
}