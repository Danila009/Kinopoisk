package com.example.kinopoisk.screen.filmInfo

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.kinopoisk.R
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.filmInfo.Budget
import com.example.kinopoisk.api.model.filmInfo.Fact
import com.example.kinopoisk.api.model.filmInfo.SequelAndPrequel
import com.example.kinopoisk.api.model.filmInfo.Similar
import com.example.kinopoisk.api.model.seasons.Season
import com.example.kinopoisk.api.model.staff.Staff
import com.example.kinopoisk.navigation.Screen
import com.example.kinopoisk.screen.filmInfo.view.WebView
import com.example.kinopoisk.ui.theme.primaryBackground
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters
import com.example.kinopoisk.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FilmInfoScreen(
    filmInfoViewModel: FilmInfoViewModel = hiltViewModel(),
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
    filmId: Int,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val checkWeb = remember { mutableStateOf(false) }
    val filmInfo = remember { mutableStateOf(FilmInfo()) }
    val budget = remember { mutableStateOf(Budget()) }
    val fact = remember { mutableStateOf(Fact()) }
    val staff = remember { mutableStateOf(listOf<Staff>()) }
    val similar = remember { mutableStateOf(Similar()) }
    val sequelAndPrequel = remember { mutableStateOf(listOf<SequelAndPrequel>()) }
    val season = remember { mutableStateOf(Season()) }
    val imageBitmapState = remember { mutableStateOf(
        Converters().toBitmap(
            R.drawable.image,context
        )
    ) }

    filmInfoViewModel.getFilmInfo(filmId)
    filmInfoViewModel.responseFilmInfo.onEach {
        filmInfo.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getBudget(filmId)
    filmInfoViewModel.responseBudget.onEach {
        budget.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getFact(filmId)
    filmInfoViewModel.responseFact.onEach {
        fact.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getStaff(filmId)
    filmInfoViewModel.responseStaff.onEach {
        staff.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getSimilar(filmId)
    filmInfoViewModel.responseSimilar.onEach {
        similar.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getSequelAndPrequel(filmId)
    filmInfoViewModel.responseSequelAndPrequel.onEach {
        sequelAndPrequel.value = it
    }.launchWhenStarted(lifecycleScope)

    filmInfoViewModel.getSeason(filmId)
    filmInfoViewModel.responseSeason.onEach {
        season.value = it
    }.launchWhenStarted(lifecycleScope)

    scope.launch {
        filmInfo.value.posterUrlPreview?.let {
            imageBitmapState.value = Converters().bitmapCoil(it,context)
        }
    }

    if (checkWeb.value){
        WebView(url = filmInfo.value.webUrl!!)
    }else{
        Scaffold(
            topBar = {
                TopAppBar(
                    elevation = 8.dp,
                    backgroundColor = primaryBackground,
                    title = {
                        Text(text = filmInfo.value.nameRu)
                    }, navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screen.Main.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                    }
                )
            }, content = {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = primaryBackground
                ) {
                    LazyColumn(content = {
                        item {
                            Column {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        bitmap = imageBitmapState.value.asImageBitmap(),
                                        contentDescription = null
                                    )
                                    LazyRow(content = {
                                        items(filmInfo.value.genres){
                                            Text(
                                                text = it.genre,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    })
                                    LazyRow(content = {
                                        items(filmInfo.value.countries){
                                            Text(
                                                text = "${it.country} ",
                                                modifier = Modifier.padding(bottom =  5.dp)
                                            )
                                        }
                                    })
                                }

                                if (filmInfo.value.serial){
                                    season.value.total?.let {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Column {
                                                Text(
                                                    text = "Сезоны и серии:",
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight.Bold,
                                                    color = secondaryBackground
                                                )
                                                Text(
                                                    text = "${season.value.total} Сезона",
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }
                                            TextButton(
                                                onClick = { /*TODO*/ },
                                                modifier = Modifier.padding(5.dp)
                                            ) {
                                                Text(
                                                    text = "Все ->",
                                                    color = secondaryBackground
                                                )
                                            }
                                        }
                                    }
                                }

                                filmInfo.value.slogan?.let {
                                    Text(
                                        text = "Слоган:",
                                        modifier = Modifier.padding(5.dp),
                                        fontWeight = FontWeight.Bold,
                                        color = secondaryBackground
                                    )

                                    Text(
                                        text = it,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }

                                filmInfo.value.description?.let {
                                    Text(
                                        text = "Description:",
                                        modifier = Modifier.padding(5.dp),
                                        fontWeight = FontWeight.Bold,
                                        color = secondaryBackground
                                    )
                                    Text(
                                        text = it,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }

                                if (staff.value.isNotEmpty()){
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Актёры",
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )

                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Text(
                                                text = "Все ->",
                                                color = secondaryBackground
                                            )
                                        }
                                    }
                                    LazyRow(content = {
                                        items(staff.value){ item ->
                                            if (item.professionKey == "ACTOR"){
                                                val bitmapIconState = remember { mutableStateOf(Converters().toBitmap(R.drawable.image, context)) }
                                                scope.launch {
                                                    item.posterUrl?.let {
                                                        bitmapIconState.value = Converters().bitmapCoil(it, context)
                                                    }
                                                }
                                                Card(
                                                    shape = AbsoluteRoundedCornerShape(7.dp),
                                                    modifier = Modifier.padding(5.dp)
                                                ) {
                                                    Row(
                                                        modifier = Modifier.padding(5.dp)
                                                    ) {
                                                        Image(
                                                            bitmap = bitmapIconState.value.asImageBitmap(),
                                                            contentDescription = null,
                                                            modifier = Modifier
                                                                .padding(5.dp)
                                                                .height(80.dp)
                                                                .width(50.dp)
                                                        )
                                                        Column {
                                                            Text(
                                                                text = item.nameRu,
                                                                modifier = Modifier.padding(
                                                                    start = 5.dp,
                                                                    top = 3.dp,
                                                                    bottom = 2.dp
                                                                )
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    })
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Съёмочная группа:",
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )

                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Text(
                                                text = "Все ->",
                                                color = secondaryBackground
                                            )
                                        }
                                    }
                                    LazyRow(content = {
                                        items(staff.value){ item ->
                                            if (
                                                item.professionKey == "DIRECTOR"
                                                || item.professionKey == "EDITOR"
                                                || item.professionKey == "DESIGN"
                                                || item.professionKey == "COMPOSER"
                                                || item.professionKey == "OPERATOR"
                                                || item.professionKey == "WRITER"
                                                || item.professionKey == "VOICE_DIRECTOR"
                                                || item.professionKey == "PRODUCER"
                                            ){
                                                val bitmapIconState = remember { mutableStateOf(Converters().toBitmap(R.drawable.image, context)) }
                                                scope.launch {
                                                    item.posterUrl?.let {
                                                        bitmapIconState.value = Converters().bitmapCoil(it, context)
                                                    }
                                                }
                                                Card(
                                                    shape = AbsoluteRoundedCornerShape(7.dp),
                                                    modifier = Modifier.padding(5.dp)
                                                ) {
                                                    Row(
                                                        modifier = Modifier.padding(5.dp)
                                                    ) {
                                                        Image(
                                                            bitmap = bitmapIconState.value.asImageBitmap(),
                                                            contentDescription = null,
                                                            modifier = Modifier
                                                                .padding(5.dp)
                                                                .height(80.dp)
                                                                .width(50.dp)
                                                        )
                                                        Column {
                                                            Text(
                                                                text = item.nameRu,
                                                                modifier = Modifier.padding(
                                                                    start = 5.dp,
                                                                    top = 3.dp,
                                                                    bottom = 2.dp
                                                                )
                                                            )
                                                            Text(
                                                                text = item.professionText,
                                                                modifier = Modifier.padding(
                                                                    start = 5.dp,
                                                                    top = 2.dp,
                                                                    bottom = 3.dp
                                                                )
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    })
                                }

                                if (fact.value.items.isNotEmpty()){
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Знаете ли вы, что...",
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )

                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Text(
                                                text = "Все ->",
                                                color = secondaryBackground
                                            )
                                        }
                                    }

                                    LazyRow(content = {
                                        items(fact.value.items){ item ->
                                            Card(
                                                shape = AbsoluteRoundedCornerShape(7.dp),
                                                modifier = Modifier.padding(5.dp)
                                            ) {
                                                Column {
                                                    Text(
                                                        text = if(item.type == "FACT") "Факт о фильме" else "Ощибка в фильме",
                                                        fontWeight = FontWeight.Bold,
                                                        modifier = Modifier.padding(5.dp)
                                                    )

                                                    Text(
                                                        text = item.text,
                                                        modifier = Modifier.padding(5.dp)
                                                    )
                                                }
                                            }
                                        }
                                    })
                                }

                                if (budget.value.items.isNotEmpty()){
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Прокат",
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )

                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Text(
                                                text = "Все ->",
                                                color = secondaryBackground
                                            )
                                        }
                                    }
                                    LazyRow(content = {
                                        items(budget.value.items){ item ->
                                            Card(
                                                shape = AbsoluteRoundedCornerShape(7.dp),
                                                modifier = Modifier.padding(5.dp)
                                            ) {
                                                Column {
                                                    Text(
                                                        text = item.type,
                                                        modifier = Modifier.padding(5.dp)
                                                    )
                                                    Text(
                                                        text = "${item.amount} ${item.symbol}",
                                                        modifier = Modifier.padding(5.dp)
                                                    )
                                                }
                                            }
                                        }
                                    })
                                }
                                if (sequelAndPrequel.value.isNotEmpty()){
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Сиквелы и прикелы:",
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )

                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Text(
                                                text = "Все ->",
                                                color = secondaryBackground
                                            )
                                        }
                                    }
                                    LazyRow(content = {
                                        items(sequelAndPrequel.value){ item ->
                                            val bitmapIconState = remember { mutableStateOf(Converters().toBitmap(R.drawable.image, context)) }
                                            scope.launch {
                                                item.posterUrl?.let {
                                                    bitmapIconState.value = Converters().bitmapCoil(it, context)
                                                }
                                            }
                                            Column(
                                                modifier = Modifier.clickable {
                                                    navController.navigate(
                                                        Screen.FilmInfo.base(
                                                            filmId = item.filmId.toString()
                                                        )
                                                    )
                                                }
                                            ) {
                                                Image(
                                                    bitmap = bitmapIconState.value.asImageBitmap(),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                        .height(180.dp)
                                                        .width(140.dp)
                                                )
                                            }
                                        }
                                    })
                                }
                                if (similar.value.total.toString().isNotEmpty()){
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Похожие фильмы:",
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight.Bold,
                                            color = secondaryBackground
                                        )

                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Text(
                                                text = "Все ->",
                                                color = secondaryBackground
                                            )
                                        }
                                    }
                                    LazyRow(content = {
                                        items(similar.value.items){ item ->
                                            val bitmapIconState = remember { mutableStateOf(Converters().toBitmap(R.drawable.image, context)) }
                                            scope.launch {
                                                item.posterUrl?.let {
                                                    bitmapIconState.value = Converters().bitmapCoil(it, context)
                                                }
                                            }
                                            Column(
                                                modifier = Modifier.clickable {
                                                    navController.navigate(
                                                        Screen.FilmInfo.base(
                                                            filmId = item.filmId.toString()
                                                        )
                                                    )
                                                }
                                            ) {
                                                Image(
                                                    bitmap = bitmapIconState.value.asImageBitmap(),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                        .height(180.dp)
                                                        .width(140.dp)
                                                )
                                            }
                                        }
                                    })
                                }
                                filmInfo.value.webUrl?.let {
                                    TextButton(
                                        modifier = Modifier.padding(5.dp),
                                        onClick = { checkWeb.value = true }
                                    ) {
                                        Text(
                                            text = "КиноПоиск ->",
                                            color = secondaryBackground,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    })
                }
            }
        )
    }
}