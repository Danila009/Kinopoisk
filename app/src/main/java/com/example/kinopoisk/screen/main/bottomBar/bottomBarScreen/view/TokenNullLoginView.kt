package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun TokenNullLoginView(
    navController:NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate(Screen.Authorization.route) },
            colors = ButtonDefaults.buttonColors(backgroundColor = secondaryBackground),
            shape = AbsoluteRoundedCornerShape(20.dp),
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Authorization")
        }
        Button(
            onClick = { navController.navigate(Screen.Registration.route) },
            colors = ButtonDefaults.buttonColors(backgroundColor = secondaryBackground),
            shape = AbsoluteRoundedCornerShape(20.dp),
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Registration")
        }
    }
}