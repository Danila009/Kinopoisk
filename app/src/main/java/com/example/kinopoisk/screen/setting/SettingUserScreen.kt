package com.example.kinopoisk.screen.setting

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.constants.MainScreenConstants.Route.MAIN_ROUTE
import com.example.kinopoisk.screen.setting.viewModel.SettingViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Constants.TOKEN_SHARED

@Composable
fun SettingUserScreen(
    settingViewModel: SettingViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val token = context.getSharedPreferences(TOKEN_SHARED, Context.MODE_PRIVATE)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Setting")
                }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(MAIN_ROUTE)
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        }, content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                backgroundColor = primaryBackground
            ){
                Column {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            modifier = Modifier
                                .padding(
                                    horizontal = 15.dp,
                                    vertical = 5.dp
                                )
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = secondaryBackground
                            ), shape = AbsoluteRoundedCornerShape(15.dp),
                            onClick = {
                            settingViewModel.deleteHistoryAll()
                            Toast.makeText(context, "Delete history", Toast.LENGTH_SHORT).show()
                        }) {
                            Text(text = "Delete history")
                        }

                        Button(
                            modifier = Modifier
                                .padding(
                                    horizontal = 15.dp,
                                    vertical = 5.dp
                                )
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = secondaryBackground
                            ), shape = AbsoluteRoundedCornerShape(15.dp),
                            onClick = {
                            token.edit()
                                .putString(TOKEN_SHARED,"")
                                .apply()
                            navController.navigate(MAIN_ROUTE)
                        }) {
                            Text(text = "Выйти из аккаунта")
                        }
                    }
                }
            }
        }
    )
}