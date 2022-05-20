package com.example.feature_staff_info_more.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.animation.TextShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.staffInfoNavGraph.StaffInfoScreenRoute
import com.example.feature_staff_info_more.viewModel.StaffInfoMoreViewModel
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun StaffInfoMoreScreen(
    staffInfoViewModel:StaffInfoMoreViewModel,
    navController: NavController,
    staffId:Int
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var staff:Response<StaffInfo> by remember { mutableStateOf(Response.Loading()) }

    staffInfoViewModel.getStaffInfo(staffId)
    staffInfoViewModel.responseStaffInfo.onEach {
        staff = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                        when(staff) {
                            is Response.Error -> {
                                Text(text = "Error")
                            }
                            is Response.Loading -> {
                                TextShimmer(modifier = Modifier.fillMaxWidth(0.3f))
                            }
                            is Response.Success -> {
                                Text(text = staff.data?.nameRu ?: "")
                            }
                        }
                }, navigationIcon = {
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
                when(staff){
                    is Response.Error -> {
                        Text(
                            text = staff.message ?: "",
                            modifier = Modifier.padding(5.dp),
                            color = Color.Red
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
                                        Column {
                                            Text(
                                                text = "Карьера",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W100
                                            )

                                            Text(
                                                text = "Рост",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W100
                                            )

                                            Text(
                                                text = "Дата рождения",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W100
                                            )

                                            Text(
                                                text = "Место рождения",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W100
                                            )
                                        }

                                        Column {
                                            Text(
                                                text = staff.data?.profession ?: "",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W900
                                            )

                                            staff.data?.growth?.let {
                                                Text(
                                                    text ="${staff.data?.growth!!.toFloat() / 100} M",
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight.W900
                                                )
                                            }

                                            Text(
                                                text = staff.data?.birthday ?: "",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W900
                                            )

                                            Text(
                                                text = staff.data?.birthplace ?: "",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W900
                                            )
                                        }
                                    }

                                    staff.data?.webUrl?.let { url ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            TextButton(onClick = { navController.navigate(FilmScreenRoute.WebScreen.base(
                                                webUrl = url
                                            )) }) {
                                                Text(
                                                    text = "КиноПоиск >",
                                                    color = secondaryBackground,
                                                    fontWeight = FontWeight.W900
                                                )
                                            }
                                        }
                                    }

                                    Divider()
                                }
                            }

                            item {
                                Text(
                                    text = "Родственники:",
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.W900
                                )
                            }

                            items(staff.data?.spouses ?: emptyList()){ item ->

                                var staffRelatives:Response<StaffInfo> by remember { mutableStateOf(Response.Loading()) }

                                staffInfoViewModel.getStaffInfoReturn(id = item.personId ?: 0).onEach {
                                    staffRelatives = it
                                }.launchWhenStarted(lifecycleScope, lifecycle)

                                Row(
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate(
                                                StaffInfoScreenRoute.StaffInfo.base(
                                                    staffId = item.personId ?: 0
                                                )
                                            )
                                        },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    SubcomposeAsyncImage(
                                        model = staffRelatives.data?.posterUrl ?: "",
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
                                            text = item.name ?: "",
                                            fontWeight = FontWeight.W900,
                                            modifier = Modifier.padding(5.dp)
                                        )

                                        Text(
                                            text = item.relation ?: "",
                                            fontWeight = FontWeight.W500,
                                            modifier = Modifier.padding(5.dp)
                                        )
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