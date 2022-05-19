package com.example.kinopoisk.screen.filmTop.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_utils.common.launchWhenStarted
import com.example.kinopoisk.api.model.user.admin.filmList.AdminFilmList
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.mainNavGraph.MainScreenConstants.Route.MAIN_ROUTE
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun FilmListItemScreen(
    navController:NavController,
    lifecycleScope: LifecycleCoroutineScope,
    adminListFilmId:Int
) {
    val context = LocalContext.current

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val filmTopViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .filmTopViewModel()

    val filmListItem = remember { mutableStateOf(AdminFilmList()) }

    filmTopViewModel.getFilmListItem(id = adminListFilmId)
    filmTopViewModel.responseFilmListItem.onEach {
        filmListItem.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = filmListItem.value.title)
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigate(MAIN_ROUTE) }) {
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
                LazyColumn(modifier = Modifier.fillMaxWidth(),content = {
                    items(filmListItem.value.movies){ item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .padding(horizontal = 9.dp, vertical = 5.dp)
                                .clickable {
                                    navController.navigate(
                                        FilmScreenRoute.FilmInfo.base(
                                            item.kinopoiskId.toString()
                                        )
                                    )
                                },
                            shape = AbsoluteRoundedCornerShape(15.dp),
                            backgroundColor = primaryBackground,
                            elevation = 8.dp
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = rememberImagePainter(
                                        data = item.posterUrl,
                                        builder = {
                                            crossfade(true)
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .width(100.dp)
                                        .clip(AbsoluteRoundedCornerShape(10.dp))
                                )
                                Text(
                                    text = item.nameRu.toString(),
                                    modifier = Modifier.padding(5.dp),
                                    color = Color.White
                                )
                            }
                        }
                    }
                })
            }
        }
    )
}