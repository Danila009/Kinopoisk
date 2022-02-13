package com.example.kinopoisk.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.validate.SortingValidate
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground

@Composable
fun SortingScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val ratingFrom = remember { mutableStateOf("1") }
    val ratingTo = remember { mutableStateOf("10") }
    val yearFrom = remember { mutableStateOf("1800") }
    val yearTo = remember { mutableStateOf("2022") }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                title = { Text(text = "Sorting")},
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Main.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                Column {
                    Text(
                        text = "Рейтинг",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        OutlinedTextField(
                            value = ratingFrom.value,
                            onValueChange = { ratingFrom.value = it },
                            modifier = Modifier
                                .padding(10.dp)
                                .width(170.dp),
                            shape = AbsoluteRoundedCornerShape(20.dp),
                            label = { Text(text = "минимальный рейтинг")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = ratingTo.value,
                            onValueChange = { ratingTo.value = it },
                            modifier = Modifier
                                .padding(10.dp)
                                .width(170.dp),
                            shape = AbsoluteRoundedCornerShape(20.dp),
                            label = { Text(text = "максимальный рейтинг")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }

                    Text(
                        text = "Год",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        OutlinedTextField(
                            value = yearFrom.value,
                            onValueChange = { yearFrom.value = it },
                            modifier = Modifier
                                .padding(10.dp)
                                .width(170.dp),
                            shape = AbsoluteRoundedCornerShape(20.dp),
                            label = { Text(text = "минимальный год")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = yearTo.value,
                            onValueChange = { yearTo.value = it },
                            modifier = Modifier
                                .padding(10.dp)
                                .width(170.dp),
                            shape = AbsoluteRoundedCornerShape(20.dp),
                            label = { Text(text = "максимальный год")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            if (SortingValidate().ratingSorting(
                                  min = ratingFrom.value,
                                  max = ratingTo.value,
                                  context = context
                            )&& SortingValidate().yearSorting(
                                    min = yearFrom.value,
                                    max = yearTo.value,
                                    context = context
                            )){
                                navController.navigate(Screen.ResultSorting.base(
                                    ratingTo = ratingTo.value,
                                    ratingFrom = ratingFrom.value,
                                    yearTo = yearTo.value,
                                    yearFrom = yearFrom.value
                                ))
                            }
                        },
                        modifier = Modifier
                            .padding(
                                vertical = 10.dp,
                                horizontal = 12.dp
                            )
                            .height(60.dp)
                            .fillMaxWidth(),
                        shape = AbsoluteRoundedCornerShape(20.dp)
                    ) {
                        Text(text = "Search")
                    }
                }
            }
        }
    )
}