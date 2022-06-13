package com.example.feature_comics_info.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_network_domain.model.marvel.comics.Result
import com.example.core_ui.animation.CinemaInfoScreenShimmer
import com.example.core_ui.view.Image
import com.example.core_utils.common.launchWhenStarted
import com.example.feature_comics_info.view.marvel.Characters
import com.example.feature_comics_info.viewModel.ComicInfoViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import kotlinx.coroutines.flow.onEach

@ExperimentalPagerApi
@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
internal fun ComicMarvel(
    viewModel:ComicInfoViewModel,
    comicId:Int
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var comic:Response<ComicsMarvel> by remember { mutableStateOf(Response.Loading()) }

    viewModel.getComicById(comicId)
    viewModel.responseComic.onEach {
        comic = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    val characters = viewModel.getComicCharacters(
        comicId
    ).collectAsLazyPagingItems()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            when(comic){
                is Response.Error -> { item { Text(text = comic.message ?: "") } }
                is Response.Loading -> item { CinemaInfoScreenShimmer() }
                is Response.Success -> {
                    itemsIndexed(comic.data?.data?.results ?: emptyList()) { index, item ->
                        if (index == 0){ ComicsMarvelItem(item, characters) }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    )
}

@ExperimentalPagerApi
@Composable
private fun ComicsMarvelItem(
    result: Result,
    characters: LazyPagingItems<com.example.core_network_domain.model.marvel.character.Result>
) {
    if (result.images != null){
        HorizontalPager(count = result.images?.size ?: 0) { page ->
            repeat(result.images?.size ?: 0){ index ->
                if (page == index){
                    Image(
                        url = "${result.images?.get(index)?.path}.${result.images?.get(index)?.extension}",
                        modifier = Modifier
                            .size(300.dp)
                            .padding(5.dp),
                    )
                }
            }
        }
    }else {
        Image(
            url = "${result.thumbnail?.path}.${result.thumbnail?.extension}",
            modifier = Modifier
                .size(300.dp)
                .padding(5.dp),
        )
    }

    Text(
        text = result.title ?: "",
        modifier = Modifier.padding(5.dp),
        fontWeight = FontWeight.W900,
        textAlign = TextAlign.Center
    )
    
    Column {

        result.description?.let { description ->
            if (description.isNotEmpty()){
                Row {
                    Text(
                        text = "Description",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )

                    Spacer(modifier = Modifier.height(60.dp))

                    Text(
                        text = result.description ?: "",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }

        result.modified?.let { modified ->
            if(modified.isNotEmpty()){
                Row {
                    Text(
                        text = "Modified",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )

                    Spacer(modifier = Modifier.height(60.dp))

                    Text(
                        text = result.modified ?: "",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }

        result.isbn?.let { isbn ->
            if (isbn.isNotEmpty()){
                Row {
                    Text(
                        text = "Isbn",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )

                    Spacer(modifier = Modifier.height(60.dp))

                    Text(
                        text = result.isbn ?: "",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }

        result.upc?.let { upc ->
            if (upc.isNotEmpty()){
                Row {
                    Text(
                        text = "Upc",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )

                    Spacer(modifier = Modifier.height(60.dp))

                    Text(
                        text = result.upc ?: "",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }

        result.format?.let { format ->
            if (format.isNotEmpty()){
                Row {
                    Text(
                        text = "Format",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )

                    Spacer(modifier = Modifier.height(60.dp))

                    Text(
                        text = result.format ?: "",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }

        result.ean?.let { ean ->
            if(ean.isNotEmpty()){
                Row {
                    Text(
                        text = "Ean",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )

                    Spacer(modifier = Modifier.height(60.dp))

                    Text(
                        text = result.ean ?: "",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }

        result.issn?.let { issn ->
            if (issn.isNotEmpty()){
                Row {
                    Text(
                        text = "Issn",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )

                    Spacer(modifier = Modifier.height(60.dp))

                    Text(
                        text = result.issn ?: "",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }

        result.diamondCode?.let { diamondCode ->
            if (diamondCode.isNotEmpty()){
                Row {
                    Text(
                        text = "Diamond Code",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W100
                    )

                    Spacer(modifier = Modifier.height(60.dp))

                    Text(
                        text = result.diamondCode ?: "",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.W900
                    )
                }
            }
        }

        result.prices?.let { prices ->
            if(prices.isNotEmpty()){
                Text(
                    text = "Prices",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.W900
                )

                LazyRow(content = {
                    items(prices){ item ->
                        Card(
                            shape = AbsoluteRoundedCornerShape(15.dp),
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Column {
                                Text(
                                    text = item.type ?: "",
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.W100
                                )

                                Text(
                                    text = item.price.toString(),
                                    modifier = Modifier.padding(5.dp),
                                    fontWeight = FontWeight.W900
                                )
                            }
                        }
                    }
                })
            }
        }

        result.dates?.let { dates ->
            if (dates.isNotEmpty()){
                Text(
                    text = "Dates",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.W900
                )
            }
        }

        LazyRow(content = {
            items(result.dates ?: emptyList()){ item ->
                Card(
                    shape = AbsoluteRoundedCornerShape(15.dp),
                    modifier = Modifier.padding(5.dp)
                ) {
                    Column {
                        Text(
                            text = item.type ?: "",
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight.W100
                        )

                        Text(
                            text = item.date ?: "",
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight.W900
                        )
                    }
                }
            }
        })

        Characters(characters = characters)
    }
}