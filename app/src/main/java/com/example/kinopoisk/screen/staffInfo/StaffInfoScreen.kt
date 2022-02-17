package com.example.kinopoisk.screen.staffInfo

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kinopoisk.api.model.staff.StaffInfo
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.key.StaffInfoScreenKey
import com.example.kinopoisk.screen.staffInfo.view.ProfessionViewState
import com.example.kinopoisk.screen.staffInfo.viewState.ProfessionKeyViewState
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.onEach

@ExperimentalPagerApi
@Composable
fun StaffInfoScreen(
    staffInfoViewModel: StaffInfoViewModel = hiltViewModel(),
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    staffId:Int,
    filmId:String?,
    keyStaffInfoScreenString: String
) {
    val statePager = rememberPagerState(pageCount = 4)
    val staffInfo = remember { mutableStateOf(StaffInfo()) }

    val keyStaffInfoScreen = Converters().decodeFromString<StaffInfoScreenKey>(keyStaffInfoScreenString)

    staffInfoViewModel.getStaffInfo(staffId)
    staffInfoViewModel.responseStaffInfo.onEach {
        staffInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = staffInfo.value.nameRu.toString())
                }, navigationIcon = {
                    IconButton(onClick = {
                        when(keyStaffInfoScreen){
                            StaffInfoScreenKey.FILM -> {
                                filmId?.let {
                                    navController.navigate(Screen.FilmInfo.base(it))
                                }
                            }
                            StaffInfoScreenKey.PERSON -> {
                                navController.navigate(Screen.Main.route)
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                LazyColumn(content = {
                    item {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Image(
                                    painter = rememberImagePainter(
                                        data = staffInfo.value.posterUrl,
                                        builder = {
                                            crossfade(true)
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .height(200.dp)
                                        .width(250.dp)
                                )
                                Column {
                                    Text(
                                        text = staffInfo.value.nameRu.toString(),
                                        modifier = Modifier.padding(5.dp),
                                        color = Color.White
                                    )
                                    Text(
                                        text = staffInfo.value.nameEn.toString(),
                                        modifier = Modifier.padding(5.dp),
                                        color = Color.White
                                    )
                                    Text(
                                        text = staffInfo.value.profession.toString(),
                                        modifier = Modifier.padding(5.dp),
                                        color = Color.White
                                    )
                                    Text(
                                        text = Converters().getTime(staffInfo.value.birthday.toString()),
                                        modifier = Modifier.padding(5.dp),
                                        color = Color.White
                                    )
                                    Text(
                                        text = "${staffInfo.value.age} лет",
                                        modifier = Modifier.padding(5.dp),
                                        color = Color.White
                                    )
                                    TextButton(onClick = { navController.navigate(
                                        Screen.MoreStaff.base(
                                            staffIf = staffId.toString(),
                                            filmId = filmId.toString()
                                        )) }) {

                                        Text(
                                            text = "Подробнее ->",
                                            modifier = Modifier.padding(5.dp),
                                            color = secondaryBackground
                                        )
                                    }
                                }
                            }
                        }
                    }
                    items(staffInfo.value.films){ item ->
                        HorizontalPager(state = statePager) {
                            when(it){
                                0 -> {
                                    ProfessionViewState(
                                        professionKeyViewState = ProfessionKeyViewState.ACTOR,
                                        navController = navController,
                                        item = item
                                    )
                                }
                            }
                        }
                    }
                })
            }
        }
    )
}