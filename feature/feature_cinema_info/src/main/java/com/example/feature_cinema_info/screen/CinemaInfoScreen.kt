package com.example.feature_cinema_info.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.*
import com.example.core_ui.animation.CinemaInfoScreenShimmer
import com.example.core_ui.animation.FilmListShimmer
import com.example.core_ui.animation.TextShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.cinemaNavGraph.CinemaScreenRoute
import com.example.core_utils.navigation.mainNavGraph.MainScreenRoute
import com.example.feature_cinema_info.view.WebView
import com.example.feature_cinema_info.viewModel.CinemaInfoViewModel
import com.example.feature_cinema_info.view.PhoneView
import com.example.feature_cinema_info.view.ReviewView
import com.example.feature_cinema_info.view.ScheduleView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.onEach

@ExperimentalMaterialApi
@SuppressLint("FlowOperatorInvokedInComposition")
@ExperimentalPagerApi
@Composable
fun CinemaInfoScreen(
    navController: NavController,
    cinemaId:Int,
    cinemaInfoViewModel:CinemaInfoViewModel
) {
    val context = LocalContext.current
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var cinema: Response<Cinema> by remember { mutableStateOf(Response.Loading()) }
    var cinemaPhotos by remember { mutableStateOf(listOf<Photo>()) }
    var cinemaPhone by remember { mutableStateOf(listOf<Phone>()) }
    var cinemaSchedule by remember { mutableStateOf(listOf<Schedule>()) }
    var cinemaReview:Response<Review> by remember { mutableStateOf(Response.Loading()) }

    val pagerStateImage = rememberPagerState(pageCount = cinemaPhotos.size)

    var statusRegistration by remember { mutableStateOf(false) }

    cinemaInfoViewModel.getCinemaById(id = cinemaId)
    cinemaInfoViewModel.responseCinema.onEach {
        cinema = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    cinemaInfoViewModel.getCinemaPhotos(id = cinemaId)
    cinemaInfoViewModel.responseCinemaPhotos.onEach {
        cinemaPhotos = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    cinemaInfoViewModel.responseStatusRegistration.onEach {
        statusRegistration = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    cinemaInfoViewModel.getCinemaPhone(id = cinemaId)
    cinemaInfoViewModel.responseCinemaPhone.onEach {
        cinemaPhone = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    cinemaInfoViewModel.getCinemaSchedule(id = cinemaId)
    cinemaInfoViewModel.responseCinemaSchedule.onEach {
        cinemaSchedule = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    cinemaInfoViewModel.getCinemaReview(
        id = cinemaId,
        search = "",
        startDate = null,
        endDate = null,
        startRating = null,
        endRating = null
    )
    cinemaInfoViewModel.responseCinemaReview.onEach {
        cinemaReview = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    when(cinema){
                        is Response.Error -> {
                            Text(
                                text = "Error",
                                color = Color.Red
                            )
                        }
                        is Response.Loading -> {
                            TextShimmer(
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .height(15.dp)
                            )
                        }
                        is Response.Success -> {
                            Text(text = cinema.data!!.title)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
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
                when(cinema){
                    is Response.Error -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = cinema.message.toString(),
                                color = Color.Red,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    is Response.Loading -> {
                        CinemaInfoScreenShimmer()
                    }
                    is Response.Success -> {
                        cinema.data?.let { data ->
                            LazyColumn(content = {
                                item {
                                    HorizontalPager(
                                        state = pagerStateImage,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        repeat(cinemaPhotos.size){ index ->
                                            if (index == it){
                                                Image(
                                                    painter = rememberImagePainter(
                                                        data = cinemaPhotos[index].photo,
                                                        builder = {
                                                            crossfade(true)
                                                        }
                                                    ), contentDescription = null,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(300.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                                item{
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        LazyRow(content = {
                                            item {
                                                if (data.has3D){
                                                    Text(
                                                        text = "3D",
                                                        modifier = Modifier
                                                            .padding(5.dp),
                                                        color = secondaryBackground
                                                    )
                                                }
                                                if (data.hasImax){
                                                    Text(
                                                        text = "IMAX",
                                                        modifier = Modifier
                                                            .padding(5.dp),
                                                        color = secondaryBackground
                                                    )
                                                }
                                                if (data.has4DX){
                                                    Text(
                                                        text = "4DX",
                                                        modifier = Modifier
                                                            .padding(5.dp),
                                                        color = secondaryBackground
                                                    )
                                                }
                                                Text(
                                                    text = data.rating.toString(),
                                                    modifier = Modifier
                                                        .padding(5.dp),
                                                    color = secondaryBackground
                                                )
                                            }
                                        })

                                        WebView(
                                            navController = navController,
                                            cinema = data
                                        )
                                    }
                                }

                                item {
                                    Text(
                                        text = "Телефон:",
                                        modifier = Modifier.padding(5.dp),
                                        color = secondaryBackground
                                    )
                                }

                                items(cinemaPhone){item ->
                                    PhoneView(
                                        phoneItem = item,
                                        context = context
                                    )
                                }

                                item {
                                    Text(
                                        text = "График работы:",
                                        modifier = Modifier.padding(5.dp),
                                        color = secondaryBackground
                                    )
                                }

                                items(cinemaSchedule){ item ->
                                    ScheduleView(
                                        schedule = item
                                    )
                                }

                                item {
                                    Column {
                                        Text(
                                            text = "Друрая информация:",
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )
                                        Text(
                                            text = data.description,
                                            modifier = Modifier.padding(5.dp)
                                        )
                                    }
                                }

                                item {
                                    Text(
                                        text = "Отзыввы:",
                                        modifier = Modifier.padding(5.dp),
                                        color = secondaryBackground
                                    )

                                    if (statusRegistration){
                                        TextButton(
                                            modifier = Modifier.padding(5.dp),
                                            onClick = {
                                                navController.navigate(
                                                    if (statusRegistration){
                                                        CinemaScreenRoute.AddReviewCinema.base(
                                                            cinemaId = cinemaId
                                                        )
                                                    }else{
                                                        MainScreenRoute.MainRoute.Profile.route
                                                    }
                                                )
                                            }) {
                                            Text(
                                                text = "Добавить отзыв ->",
                                                modifier = Modifier.padding(5.dp),
                                                color = secondaryBackground
                                            )
                                        }
                                    }
                                }

                                when(cinemaReview){
                                    is Response.Error -> {
                                        item {
                                            Text(
                                                text = cinemaReview.message.toString(),
                                                color = Color.Red,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                    is Response.Loading -> {
                                        item {
                                            FilmListShimmer()
                                        }
                                    }
                                    is Response.Success -> {
                                        items(cinemaReview.data!!.items){ item ->
                                            ReviewView(
                                                review = item
                                            )
                                        }
                                    }
                                }

                                item {
                                    Spacer(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(60.dp)
                                    )
                                }
                            })
                        }
                    }
                }
            }
        }
    )
}