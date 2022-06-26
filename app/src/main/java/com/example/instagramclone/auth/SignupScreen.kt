package com.example.instagramclone.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instagramclone.IgViewModel
import com.example.instagramclone.R

@Composable
fun SignupScreen(navController: NavController, vm: IgViewModel) {
  Box(modifier = Modifier.fillMaxSize().padding(30.dp)) {
    Column(
      modifier = Modifier.fillMaxWidth().wrapContentHeight().verticalScroll(
        rememberScrollState()
      )
    ) {
      val usernameState = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
      val emailState = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
      val passwordState = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

      Image(
        painter = painterResource(id = R.drawable.ig_logo),
        contentDescription = null,
        modifier = Modifier.width(250.dp).height(250.dp).padding(8.dp)
      )
      Text(
        text = "Sign Up",
        modifier = Modifier.padding(8.dp),
        fontSize = 30.sp,
        fontFamily = FontFamily.SansSerif
      )
      OutlinedTextField(
        value = usernameState.value,
        onValueChange = { usernameState.value = it },
        modifier = Modifier.padding(8.dp),
        label = { Text(text = "Username") },
      )
      OutlinedTextField(
        value = emailState.value,
        onValueChange = { emailState.value = it },
        modifier = Modifier.padding(8.dp),
        label = { Text(text = "Email") },
      )
      OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        modifier = Modifier.padding(8.dp),
        label = { Text(text = "Password") },
      )
      Button(
        onClick = {},
        modifier = Modifier.padding(8.dp),
      ) {
        Text(text = "Sign Up")
      }
      Text(
        text = "Already have an account? Go to login -->",
        modifier = Modifier.padding(8.dp).clickable { },
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif
      )
    }
  }
}