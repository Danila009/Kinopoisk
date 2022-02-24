package com.example.kinopoisk.screen.main.sortingScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.api.model.filmInfo.Countrie
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.viewModel.MainViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach
import java.util.ArrayList

@Composable
fun CountriesScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
) {
    val countriesCheck = remember { mutableStateOf(ArrayList<Countrie>()) }
    val countries = remember { mutableStateOf(listOf<Countrie>()) }

    mainViewModel.getFilter()
    mainViewModel.responseFilter.onEach {
        countries.value = it.countries
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = { Text(text = "Countries") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Sorting.base())
                    }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }, actions = {
                    TextButton(onClick = {
                        navController.navigate(Screen.Sorting.base(
                            countries = Converters().encodeToString(countriesCheck.value)
                        ))
                    }) {
                        Text(
                            text = "Применить",
                            color = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ){
                LazyColumn(content = {
                    items(countries.value){ item ->
                        val checkCountrie = remember { mutableStateOf(false) }
                        if (checkCountrie.value){
                            countriesCheck.value.add(item)
                        }else{
                            countriesCheck.value.remove(item)
                        }
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        checkCountrie.value = !checkCountrie.value
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item.country,
                                    modifier = Modifier.padding(5.dp)
                                )
                                Checkbox(
                                    checked = checkCountrie.value,
                                    onCheckedChange = {checkCountrie.value = it},
                                    modifier = Modifier.padding(5.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = secondaryBackground
                                    )
                                )
                            }
                            Divider()
                        }
                    }
                })
            }
        }
    )
}