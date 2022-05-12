package com.example.feature_profile.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.model.movie.Movie
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.model.staff.Staff
import com.example.core_network_domain.model.user.User
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.Constants.IMAGE_NO_PHOTO_URL
import com.example.core_utils.common.launchWhenStarted
import com.example.core_utils.navigation.settingNavGraph.SettingScreenConstants.Route.SETTING_ROUTE
import com.example.feature_profile.viewModel.ProfileViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

@SuppressLint("FlowOperatorInvokedInComposition")
@ExperimentalSerializationApi
@Composable
fun StatusRegistrationTrueView(
    profileViewModel: ProfileViewModel,
    navController:NavController
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val userInfo = remember { mutableStateOf(User()) }
    var favoriteMovie by remember { mutableStateOf(Movie()) }
    var favoriteStaff by remember { mutableStateOf(Staff()) }
    val checkImageDialog = remember { mutableStateOf(false) }
    var historyMovie by remember { mutableStateOf(HistoryMovie()) }

    profileViewModel.getHistoryMovie()
    profileViewModel.responseHistoryMovie.onEach {
        historyMovie = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    profileViewModel.getUserInfo()
    profileViewModel.responseUserInfo.onEach {
        userInfo.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    profileViewModel.responseUserFavoriteMovie.onEach {
        favoriteMovie = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    profileViewModel.responseUserFavoriteStaff.onEach {
        favoriteStaff = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = primaryBackground
    ) {
        if (checkImageDialog.value){
            DialogPhotoView(
                checkDialog = checkImageDialog,
                profileViewModel = profileViewModel
            )
        }

        LazyColumn(content = {
            item {
                Column {
                    TopAppBar(
                        backgroundColor = primaryBackground,
                        elevation = 8.dp,
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = { checkImageDialog.value = true }) {
                                    SubcomposeAsyncImage(
                                        model = if (userInfo.value.photo != null) userInfo.value.photo else "" +
                                                IMAGE_NO_PHOTO_URL,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(80.dp)
                                            .clip(CircleShape)
                                    ) {
                                        val state = painter.state
                                        if (
                                            state is AsyncImagePainter.State.Loading ||
                                            state is AsyncImagePainter.State.Error
                                        ) {
                                            ImageShimmer(
                                                modifier = Modifier
                                                    .clip(CircleShape)
                                            )
                                        } else {
                                            SubcomposeAsyncImageContent()
                                        }
                                    }
                                }

                                Text(
                                    text = userInfo.value.username,
                                    color = secondaryBackground
                                )
                            }
                        }, actions = {
                            IconButton(onClick = { navController.navigate(SETTING_ROUTE) }) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = null,
                                    tint = secondaryBackground
                                )
                            }
                        }
                    )

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            ){
                                append("Баланс: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = secondaryBackground
                                )
                            ){
                                append("${userInfo.value.balance} P")
                            }
                        },modifier = Modifier.padding(5.dp)
                    )

                    FavoriteFilmView(
                        navController = navController,
                        movie = favoriteMovie
                    )

                    FavoriteStaffView(
                        navController = navController,
                        staff = favoriteStaff
                    )

                    HistoryMovieView(
                        navController = navController,
                        historyMovie = historyMovie
                    )

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                    )
                }
            }
        })
    }
}