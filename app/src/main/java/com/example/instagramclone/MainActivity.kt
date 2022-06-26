package com.example.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.auth.SignupScreen
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      InstagramCloneTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
         instagramApp()
        }
      }
    }
  }
}

sealed class Destination(val route: String) {
  object Signup : Destination("signup")
//  object Profile : Destination()
//  object Search : Destination()
//  object Notifications : Destination()
//  object Messages : Destination()
}

@Composable
fun instagramApp() {
  val vm = hiltViewModel<IgViewModel>()
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = Destination.Signup.route){
    composable(Destination.Signup.route) {
      SignupScreen(navController = navController, vm = vm)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  InstagramCloneTheme {
    instagramApp()
  }
}