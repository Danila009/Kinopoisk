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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.core_ui.ui.theme.primaryBackground
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.di.DaggerAppComponent
import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute
import com.example.core_utils.navigation.shopNavGraph.ShopScreenRoute
import com.example.kinopoisk.screen.shop.view.DialogPriceFilmShopView

@Composable
fun ShopAddFilmItemScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val shopViewModel = DaggerAppComponent.builder()
        .context(context = context)
        .build()
        .shopViewModel()

    val filmItem = remember { mutableStateOf(FilmItem()) }
    val checkDialog = remember { mutableStateOf(false) }
    val films = shopViewModel.getFilm().collectAsLazyPagingItems()

    DialogPriceFilmShopView(
        navController = navController,
        shopViewModel = shopViewModel,
        checkDialog = checkDialog,
        filmItem = filmItem.value
    )

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Shop add film item")
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigate(ShopScreenRoute.Shop.route) }) {
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
                    items(films){ item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .padding(horizontal = 9.dp, vertical = 5.dp)
                                .clickable {
                                    navController.navigate(
                                        FilmScreenRoute.FilmInfo.base(
                                            item!!.kinopoiskId.toString()
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
                                        data = item?.posterUrl,
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

                                    IconButton(onClick = {
                                        filmItem.value = item!!
                                        checkDialog.value = true
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }
    )
}