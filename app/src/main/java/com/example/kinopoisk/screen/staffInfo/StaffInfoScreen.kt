package com.example.kinopoisk.screen.staffInfo

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.kinopoisk.api.model.user.StaffFavorite
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoScreenRoute
import com.example.kinopoisk.screen.staffInfo.view.ProfessionViewState
import com.example.kinopoisk.screen.staffInfo.viewState.ProfessionKeyViewState
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Constants.TOKEN_SHARED
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@ExperimentalPagerApi
@Composable
fun StaffInfoScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    staffId:Int
) {
    val context = LocalContext.current
    val staffInfoViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .staffInfoViewModel()

    val favoriteCheckStaff = remember { mutableStateOf(false) }
    val statePager = rememberPagerState(pageCount = 4)
    val staffInfo = remember { mutableStateOf(StaffInfo()) }
    val token = context
        .getSharedPreferences(TOKEN_SHARED, Context.MODE_PRIVATE)
        .getString(TOKEN_SHARED, "")

    staffInfoViewModel.getStaffInfo(staffId)
    staffInfoViewModel.responseStaffInfo.onEach {
        staffInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    staffInfoViewModel.getStaffFavoriteCheck(staffId = staffId)
    staffInfoViewModel.responseStaffFavoriteCheck.onEach {
        favoriteCheckStaff.value = it
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
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }, actions = {
                    if (token!!.isNotEmpty()){
                        IconButton(onClick = {
                            when(favoriteCheckStaff.value){
                                true -> {
                                    favoriteCheckStaff.value = false
                                }
                                false ->{
                                    staffInfoViewModel.postStaffFavorite(
                                        StaffFavorite(
                                            nameRu = staffInfo.value.nameRu.toString(),
                                            nameEn = staffInfo.value.nameEn.toString(),
                                            posterUrl = staffInfo.value.posterUrl.toString(),
                                            professionKey = staffInfo.value.profession.toString(),
                                            professionText = staffInfo.value.profession.toString(),
                                            staffId = staffId
                                        )
                                    )
                                    favoriteCheckStaff.value = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = if (favoriteCheckStaff.value) Color.Red else Color.Gray
                            )
                        }
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
                                        StaffInfoScreenRoute.MoreStaff.base(
                                            staffIf = staffId.toString()
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

                    item{
                        Text(
                            text = "Fact:",
                            color = secondaryBackground,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp)
                        )
                        LazyRow(content = {
                            items(staffInfo.value.facts){ item ->
                                Card(
                                    shape = AbsoluteRoundedCornerShape(7.dp),
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .width(250.dp)
                                ) {
                                    Text(
                                        text = Converters().replaceRange(item, 150),
                                        modifier = Modifier
                                            .padding(5.dp)
                                    )
                                }
                            }
                        })
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