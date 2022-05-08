package com.example.feature_settings.screen

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
import androidx.navigation.NavController
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute
import com.example.feature_settings.viewModel.SettingViewModel

@Composable
fun SettingUserScreen(
    navController: NavController,
    settingViewModel: SettingViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Setting")
                }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(MainScreenRoute.MainRoute.Profile.route)
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
                                settingViewModel.deleteHistoryMovie()
                                Toast.makeText(context, "Delete history movie",
                                    Toast.LENGTH_SHORT).show()
                        }) {
                            Text(text = "Delete history movie")
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
                                settingViewModel.deleteHistorySearch()
                                Toast.makeText(context, "Delete history search"
                                    , Toast.LENGTH_SHORT).show()
                            }) {
                            Text(text = "Delete history search")
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
                                settingViewModel.saveStatusRegistration(
                                    userRegistration = false
                                )
                                navController.navigate(MainScreenRoute.MainRoute.Profile.route)
                        }) {
                            Text(text = "Выйти из аккаунта")
                        }
                    }
                }
            }
        }
    )
}