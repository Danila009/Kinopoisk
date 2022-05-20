package com.example.kinopoisk.screen.shop

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.core_database_domain.common.UserRole
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_ui.view.SearchView
import com.example.core_utils.common.launchWhenStarted
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.mainNavGraph.MainScreenConstants.Route.MAIN_ROUTE
import com.example.core_utils.navigation.shopNavGraph.ShopScreenRoute
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun ShopScreen(
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController
) {
    val context = LocalContext.current

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val shopViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .shopViewModel()

    val userRole = remember { mutableStateOf(UserRole.BaseUser.name) }
    val search = remember { mutableStateOf("") }
    val check = remember { mutableStateOf(false) }
    val shop = shopViewModel.getShop(
        search = search.value
    ).collectAsLazyPagingItems()

    shopViewModel.readUserRole()
    shopViewModel.responseUserRole.onEach {
        userRole.value = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    SearchView(search = {
                        search.value = it
                    })
                }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(MAIN_ROUTE)
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }, actions = {
                    if (userRole.value == UserRole.Admin.name){
                        IconButton(onClick = { navController.navigate(ShopScreenRoute.ShopAddFilmItem.route) }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = secondaryBackground
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
                    items(shop){ item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .padding(horizontal = 9.dp, vertical = 5.dp)
                                .clickable {
                                    navController.navigate(
                                        FilmScreenRoute.FilmInfo.base(
                                            item?.kinopoiskId!!
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
                                        data = item?.posterUrlPreview,
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
                                Column {
                                    Text(
                                        text = item?.nameRu.toString(),
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        text = "${item?.price} P",
                                        modifier = Modifier.padding(5.dp),
                                        color = secondaryBackground
                                    )
                                }
                            }
                        }
                    }
                    item {
                        if (check.value){
                            Column(
                                Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator(
                                    color = secondaryBackground
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

                    shop.apply {
                        when{
                            loadState.refresh is LoadState.Loading -> check.value = false

                            loadState.append is LoadState.Loading -> check.value = true
                        }
                    }
                })
            }
        }
    )
}