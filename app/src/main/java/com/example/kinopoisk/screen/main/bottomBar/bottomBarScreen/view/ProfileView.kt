package com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.kinopoisk.api.model.user.UserInfo
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.launchWhenStarted
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.example.kinopoisk.R
import com.example.kinopoisk.navigation.SETTING_ROUTE
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.profile.FavoriteFilmView
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.profile.FavoriteStaffView
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.profile.HistoryView
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.profile.PurchaseView
import com.example.kinopoisk.screen.main.viewModel.MainViewModel
import com.example.kinopoisk.utils.Converters
import kotlinx.coroutines.flow.onEach

@Composable
fun ProfileView(
    mainViewModel: MainViewModel = hiltViewModel(),
    lifecycleScope: LifecycleCoroutineScope,
    navController:NavController
) {
    val context = LocalContext.current
    val userInfo = remember { mutableStateOf(UserInfo()) }
    val checkImageDialog = remember { mutableStateOf(false) }
    val bitmapImage = remember {
        mutableStateOf(
            if (userInfo.value.photo != null)
                Converters().toBitmap(userInfo.value.photo!!.photo, context)
            else Converters().toBitmap(R.drawable.icon,context)
        )
    }

    mainViewModel.getUserInfo()
    mainViewModel.responseUserInfo.onEach {
        userInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = primaryBackground
    ) {
        if (checkImageDialog.value){
            DialogPhotoView(
                mainViewModel = mainViewModel,
                checkDialog = checkImageDialog,
                bitmap = bitmapImage
            )
        }

        LazyColumn(content = {
            item {
                Column {
                    TopAppBar(
                        backgroundColor = primaryBackground,
                        elevation = 8.dp,
                        title = {
                            Row {
                                IconButton(onClick = { checkImageDialog.value = true }) {
                                    Image(bitmap = bitmapImage.value.asImageBitmap(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(30.dp)
                                            .clip(RectangleShape)
                                    )
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
                        userInfo = userInfo.value
                    )

                    FavoriteStaffView(
                        navController = navController,
                        userInfo = userInfo.value
                    )

                    PurchaseView(
                        navController = navController,
                        userInfo = userInfo.value
                    )

                    HistoryView(
                        navController = navController,
                        userInfo = userInfo.value
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