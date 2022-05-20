package com.example.feature_staff_info.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.core_ui.animation.FilmListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.common.parseHtml
import com.example.core_utils.common.replaceRange
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoScreenRoute
import com.example.feature_staff_info.viewModel.StaffInfoViewModel
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun StaffInfoScreen(
    navController: NavController,
    staffInfoViewModel:StaffInfoViewModel,
    staffId:Int
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val favoriteCheckStaff = remember { mutableStateOf(false) }
    var staffInfo: Response<StaffInfo> by remember { mutableStateOf(Response.Loading()) }

    var statusRegistration by remember { mutableStateOf(false) }

    staffInfoViewModel.getStaffInfo(staffId)
    staffInfoViewModel.responseStaffInfo.onEach {
        staffInfo = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    staffInfoViewModel.getStatusRegistration.onEach {
        statusRegistration = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Staff information")
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
                    if (statusRegistration) {
                        IconButton(onClick = {

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
                when (staffInfo) {
                    is Response.Error -> {
                        Text(
                            text = staffInfo.message ?: "",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    is Response.Loading -> {
                        CircularProgressIndicator(
                            color = secondaryBackground
                        )
                    }
                    is Response.Success -> {
                        LazyColumn(content = {
                            item {
                                Column {
                                    Row {
                                        SubcomposeAsyncImage(
                                            model = staffInfo.data?.posterUrl ?: "",
                                            contentDescription = null,
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .height(200.dp)
                                                .width(150.dp)
                                        ) {
                                            val state = painter.state
                                            if (
                                                state is AsyncImagePainter.State.Loading ||
                                                state is AsyncImagePainter.State.Error
                                            ) {
                                                ImageShimmer(
                                                    imageHeight = 200.dp,
                                                    imageWidth = 150.dp
                                                )
                                            } else {
                                                SubcomposeAsyncImageContent()
                                            }
                                        }

                                        Column {
                                            Text(
                                                text = staffInfo.data?.nameRu ?: "",
                                                fontWeight = FontWeight.W900,
                                                fontSize = 20.sp,
                                                modifier = Modifier.padding(5.dp)
                                            )

                                            Text(
                                                text = staffInfo.data?.profession ?: "",
                                                fontWeight = FontWeight.W100,
                                                modifier = Modifier.padding(5.dp)
                                            )

                                            Row {
                                                Text(
                                                    text = staffInfo.data?.birthday ?: "",
                                                    fontWeight = FontWeight.W100,
                                                    modifier = Modifier.padding(5.dp)
                                                )

                                                Text(
                                                    text = staffInfo.data?.death ?: "",
                                                    fontWeight = FontWeight.W100,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }

                                            Row {
                                                Text(
                                                    text = "${staffInfo.data?.age} лет",
                                                    fontWeight = FontWeight.W100,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                                Text(
                                                    text = "·",
                                                    fontWeight = FontWeight.W100,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                                Text(
                                                    text = "${staffInfo.data?.growth!!.toFloat() / 100} M",
                                                    fontWeight = FontWeight.W100,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }

                                            TextButton(onClick = {
                                                navController.navigate(
                                                    StaffInfoScreenRoute.MoreStaff.base(staffIf = staffId)
                                                )
                                            }) {
                                                Text(
                                                    text = "Подробнее >",
                                                    fontWeight = FontWeight.Bold,
                                                    color = secondaryBackground
                                                )
                                            }
                                        }
                                    }

                                    Divider()

                                    Text(
                                        text = "Facts: ",
                                        color = secondaryBackground,
                                        fontWeight = FontWeight.W900,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    LazyRow(content = {
                                        items(staffInfo.data?.facts ?: emptyList()) { item ->
                                            Card(
                                                shape = AbsoluteRoundedCornerShape(7.dp),
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .width(250.dp)
                                                    .height(150.dp)
                                            ) {
                                                Text(
                                                    text = replaceRange(item, 150),
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                )
                                            }
                                        }
                                    })

                                    Divider()
                                }
                            }

                            item {
                                Text(
                                    text = "Films: ",
                                    color = secondaryBackground,
                                    fontWeight = FontWeight.W900,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }

                            items(staffInfo.data?.films ?: emptyList()) { item ->

                                var filmInfo: Response<FilmInfo> by remember {
                                    mutableStateOf(
                                        Response.Loading()
                                    )
                                }

                                staffInfoViewModel.getFilmInfo(item.filmId).onEach {
                                    filmInfo = it
                                }.launchWhenStarted(lifecycleScope, lifecycle)

                                when (filmInfo) {
                                    is Response.Error -> {
                                        Column {
                                            Text(
                                                text = "Error: ${filmInfo.message}",
                                                color = Color.Red,
                                                fontWeight = FontWeight.W900,
                                                modifier = Modifier.padding(5.dp)
                                            )

                                            Divider()
                                        }
                                    }
                                    is Response.Loading -> {
                                        FilmListShimmer()
                                    }
                                    is Response.Success -> {
                                        Column(
                                            modifier = Modifier.clickable {
                                                navController.navigate(FilmScreenRoute.FilmInfo.base(filmId = item.filmId))
                                            }
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                SubcomposeAsyncImage(
                                                    model = filmInfo.data?.posterUrl,
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                        .height(150.dp)
                                                        .width(100.dp)
                                                ) {
                                                    val state = painter.state
                                                    if (
                                                        state is AsyncImagePainter.State.Loading ||
                                                        state is AsyncImagePainter.State.Error
                                                    ) {
                                                        ImageShimmer(
                                                            imageHeight = 150.dp,
                                                            imageWidth = 100.dp
                                                        )
                                                    } else {
                                                        SubcomposeAsyncImageContent()
                                                    }
                                                }

                                                Column {
                                                    Text(
                                                        text = filmInfo.data?.nameRu ?: "",
                                                        modifier = Modifier.padding(5.dp),
                                                        fontWeight = FontWeight.W900
                                                    )
                                                    Spacer(modifier = Modifier.height(5.dp))

                                                    Text(
                                                        text = item.description.toString()
                                                            .parseHtml(),
                                                        modifier = Modifier.padding(5.dp),
                                                        fontWeight = FontWeight.W900
                                                    )

                                                    Text(
                                                        text = item.professionKey?.lowercase() ?: "",
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(5.dp),
                                                        textAlign = TextAlign.Start,
                                                        fontWeight = FontWeight.W900
                                                    )
                                                }
                                            }

                                            Divider()
                                        }
                                    }
                                }
                            }
                        })
                    }
                }
            }
        }
    )
}