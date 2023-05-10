package com.alexander.intentcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexander.intentcompose.ui.theme.IntentComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val showMenu = remember {
        mutableStateOf(false)
    }
    Column {
        TopAppBar(
            title = { Text(text = "頁面轉換實例") },
            navigationIcon = {
                IconButton(onClick = {
                    Toast.makeText(context, "您點選了導覽圖示", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Navigation icon")
                }
            },
            actions = {
                IconButton(
                    onClick = { Toast.makeText(context, "Creator：黃植達", Toast.LENGTH_SHORT).show() }
                ) {
                    Icon(Icons.Rounded.AccountBox, contentDescription = "author")
                }
                IconButton(
                    onClick = { showMenu.value = true }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = null)
                }
            }
        )
        DropdownMenu(
            expanded = showMenu.value, onDismissRequest = { showMenu.value = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    navController.navigate("JumpFirst")
                })
            {
                Text(text = "Background1")
            }
            DropdownMenuItem(
                onClick =
                {
                    navController.navigate("JumpSecond")
                })
            {
                Text(text = "Background2")
            }
            DropdownMenuItem(
                onClick = {
                    navController.navigate("JumpThird")
                })
            {
                Text(text = "Background3")
            }
        }
        NavHost(navController = navController, startDestination = "JumpFirst") {
            composable("JumpFirst") {
                FirstScreen(navController = navController)
            }
            composable("JumpSecond") {
                SecondScreen(navController = navController)
            }
            composable("JumpThird") {
                ThirdScreen(navController = navController)
            }
        }
    }
}
@Composable
fun FirstScreen(navController: NavController){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                navController.navigate("JumpSecond")
            }) {
                Text(text = "I'm Background 1, Jump to 2?")
            }
            Text("Fengchia Night Market")
            Image(
                painterResource(id = R.drawable.fengjia),
                contentDescription = "Fengchia Night Market",
            )
        }
}

@Composable
fun SecondScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate("JumpThird")
        }) {
            Text(text = "I'm background 2 , Jump to 3?")
        }
        Text(text = "Mitsui Outlet Park")
        Image(
            painterResource(id = R.drawable.mitsui),
            contentDescription = "Mitsui Outlet Park",
        )
    }
}
@Composable
fun ThirdScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate("JumpFirst")
        }) {
            Text(text = "I'm Background 3, Back to 1?")
        }
        Text(text = "Shifen Waterfall")
        Image(
            painterResource(id = R.drawable.shifen),
            contentDescription = "ShiFen Waterfall",
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IntentComposeTheme {
        Greeting("Android")
    }
}