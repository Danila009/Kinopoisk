package com.example.kinopoisk.screen.shop

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.kinopoisk.navigation.MAIN_ROUTE
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.main.bottomBar.bottomBarScreen.view.SearchView
import com.example.kinopoisk.screen.shop.shopViewModel.ShopViewModel
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.UserRole
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach

@Composable
fun ShopScreen(
    shopViewModel: ShopViewModel = hiltViewModel(),
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController
) {
    val userRole = remember { mutableStateOf(UserRole.BaseUser.name) }
    val search = remember { mutableStateOf("") }
    val check = remember { mutableStateOf(false) }
    val shop = shopViewModel.getShop(
        search = search.value
    ).collectAsLazyPagingItems()

    shopViewModel.readUserRole()
    shopViewModel.responseUserRole.onEach {
        userRole.value = it
    }.launchWhenStarted(lifecycleScope)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    SearchView(search = search)
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
                        IconButton(onClick = { /*TODO*/ }) {
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
                                        Screen.FilmInfo.base(
                                            item?.kinopoiskId.toString()
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