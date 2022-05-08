package com.example.feature_serial_info.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.serial.Episode
import com.example.core_network_domain.model.serial.Season
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.launchWhenStarted
import com.example.feature_serial_info.common.Constants.RIK_AND_MORTY_ID_KINOPOISK
import com.example.feature_serial_info.state.DropdownMenuState
import com.example.feature_serial_info.view.CharacterRickAndMortyView
import com.example.feature_serial_info.view.EpisodesRickAndMortyView
import com.example.feature_serial_info.view.LocationRickAndMortyView
import com.example.feature_serial_info.viewModel.SerialInfoViewModel
import kotlinx.coroutines.flow.onEach
import java.util.ArrayList

@SuppressLint("FlowOperatorInvokedInComposition", "MutableCollectionMutableState")
@Composable
fun SerialInfoScreen(
    navController: NavController,
    filmId:Int,
    serialInfoViewModel: SerialInfoViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val season = remember { mutableStateOf(Season()) }
    val seasonEpisode = remember { mutableStateOf(ArrayList<Episode>()) }
    val filmInfo = remember { mutableStateOf(FilmInfo()) }

    var baseExpandedDropdownMenu by remember { mutableStateOf(false) }
    var dropdownMenuState by remember { mutableStateOf(DropdownMenuState.EPISODES) }

    val charactersRickAndMortySource = serialInfoViewModel.getCharactersRickAndMorty(

    ).collectAsLazyPagingItems()

    val locationsRickAndMortySource = serialInfoViewModel.getLocationsRickAndMorty(

    ).collectAsLazyPagingItems()

    serialInfoViewModel.getFilmInfo(filmId)
    serialInfoViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

   serialInfoViewModel.getSeason(filmId)
   serialInfoViewModel.responseSeason.onEach {
       season.value = it
   }.launchWhenStarted(lifecycleScope, lifecycle)

    if (season.value.items.isNotEmpty()){
        season.value.items.forEach { item ->
            item.episodes.forEach {
                seasonEpisode.value.add(it)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = filmInfo.value.nameRu)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }, actions = {
                    if (RIK_AND_MORTY_ID_KINOPOISK == filmId){
                        TextButton(onClick = {
                            baseExpandedDropdownMenu = true
                        }) {
                            Text(
                                text = dropdownMenuState.name.lowercase(),
                                color = secondaryBackground
                            )
                        }
                        DropdownMenu(
                            modifier = Modifier.background(primaryBackground),
                            expanded = baseExpandedDropdownMenu,
                            onDismissRequest = { baseExpandedDropdownMenu = false }
                        ) {
                            DropdownMenuState.values().forEach { item ->
                                DropdownMenuItem(onClick = {
                                    dropdownMenuState = item
                                    baseExpandedDropdownMenu = false
                                }) {
                                    Text(
                                        text = item.name.lowercase(),
                                        color = if (dropdownMenuState == item) secondaryBackground else
                                            Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }, content = {
            if (seasonEpisode.value.isNotEmpty()){
//                LazyColumn(content = {
//                    items(seasonEpisode.value){
//                        Text(text = it.toString())
//                    }
//                })
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = primaryBackground
                ) {
                    LazyColumn(content = {
                        when(dropdownMenuState){
                            DropdownMenuState.CHARACTERS -> {
                                items(charactersRickAndMortySource){ item ->
                                    CharacterRickAndMortyView(
                                        characterItem = item!!
                                    )
                                }
                            }
                            DropdownMenuState.LOCATIONS -> {
                                items(locationsRickAndMortySource){ item ->
                                    LocationRickAndMortyView(
                                        locationItem = item!!
                                    )
                                }
                            }
                            DropdownMenuState.EPISODES -> {
                                items(seasonEpisode.value){ item ->
                                    EpisodesRickAndMortyView(
                                        episode = item
                                    )
                                }
                            }
                        }
                        item {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            )
                        }
                    })
                }
            }
        }
    )
}