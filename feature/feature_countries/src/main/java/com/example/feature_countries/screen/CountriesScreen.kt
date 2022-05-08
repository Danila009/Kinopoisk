package com.example.feature_countries.screen

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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.core_network_domain.model.movie.Countrie
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.encodeToString
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.sortingScreenNavGraph.SortingScreenRoute
import com.example.feature_countries.viewModel.CountriesViewModel
import kotlinx.coroutines.flow.onEach
import java.util.ArrayList

@Composable
fun CountriesScreen(
    navController: NavController,
    countriesViewModel: CountriesViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val countriesCheck = remember { mutableStateOf(ArrayList<Countrie>()) }
    val countries = remember { mutableStateOf(listOf<Countrie>()) }

    countriesViewModel.getFilter()
    countriesViewModel.responseFilter.onEach {
        countries.value = it.countries
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = { Text(text = "Countries") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(SortingScreenRoute.SortingFilm.base())
                    }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }, actions = {
                    TextButton(onClick = {
                        navController.navigate(SortingScreenRoute.SortingFilm.base(
                            countries = encodeToString(countriesCheck.value)
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