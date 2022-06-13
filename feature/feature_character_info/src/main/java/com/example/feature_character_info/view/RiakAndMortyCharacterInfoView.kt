package com.example.feature_character_info.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.enums.StatusCharacterRickAndMorty
import com.example.core_network_domain.model.rickAndMorty.CharacterItem
import com.example.core_network_domain.model.rickAndMorty.EpisodeItem
import com.example.core_ui.animation.FilmListShimmer
import com.example.core_ui.animation.ImageShimmer
import com.example.core_ui.animation.TextShimmer
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.fromStringToInt
import com.example.core_utils.common.launchWhenStarted
import com.example.feature_character_info.viewModel.CharacterInfoViewModel
import kotlinx.coroutines.flow.onEach

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
internal fun RiakAndMortyCharacterInfoView(
    riakAndMortyId:Int,
    characterInfoViewModel: CharacterInfoViewModel,
    navController: NavController
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var character:Response<CharacterItem> by remember { mutableStateOf(Response.Loading()) }

    characterInfoViewModel.getCharacterRickAndMortyById(id = riakAndMortyId)
    characterInfoViewModel.responseCharacterRickAndMorty.onEach {
        character = it
    }.launchWhenStarted(lifecycleScope, lifecycle)

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 8.dp,
                backgroundColor = primaryBackground,
                title = {
                    when(character){
                        is Response.Error -> {
                            Text(text = "Error")
                        }
                        is Response.Loading -> {
                            TextShimmer(
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .height(15.dp)
                            )
                        }
                        is Response.Success -> {
                            Text(
                                text = character.data?.name ?: "",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                when(character){
                    is Response.Error -> {
                        Text(
                            text = "Error: ${character.message}",
                            color = Red
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
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        SubcomposeAsyncImage(
                                            model = character.data?.image,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(300.dp)
                                                .clip(AbsoluteRoundedCornerShape(15.dp))
                                        ) {
                                            val state = painter.state
                                            if (
                                                state is AsyncImagePainter.State.Loading ||
                                                state is AsyncImagePainter.State.Error
                                            ) {
                                                ImageShimmer(
                                                    modifier = Modifier
                                                        .size(300.dp)
                                                        .clip(AbsoluteRoundedCornerShape(15.dp))
                                                )
                                            } else {
                                                SubcomposeAsyncImageContent()
                                            }
                                        }

                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Canvas(
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .size(15.dp)
                                            ){
                                                drawCircle(
                                                    color = when(character.data!!.status){
                                                        StatusCharacterRickAndMorty.Alive -> Color.Green
                                                        StatusCharacterRickAndMorty.Dead -> Red
                                                        StatusCharacterRickAndMorty.unknown -> Color.Gray
                                                    }
                                                )
                                            }

                                            Text(
                                                text = character.data?.status?.name ?: "",
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }

                                        Text(
                                            text = character.data?.name ?: "",
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Column {
                                            Text(
                                                text = "Species",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W100
                                            )

                                            Text(
                                                text = character.data?.species ?: "",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W900
                                            )

                                            Text(
                                                text = "Last known location",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W100
                                            )

                                            Text(
                                                text = character.data?.location?.name ?: "",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W900
                                            )
                                        }

                                        Column {
                                            Text(
                                                text = "Gender",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W100
                                            )

                                            Text(
                                                text = character.data?.gender ?: "",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W900
                                            )

                                            Text(
                                                text = "First seen in:",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W100
                                            )

                                            Text(
                                                text = character.data?.origin?.name ?: "",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W900
                                            )
                                        }
                                    }

                                    Divider()
                                }
                            }

                            items(character.data?.episode ?: emptyList()){ item ->

                                var episode:Response<EpisodeItem> by remember { mutableStateOf(Response.Loading()) }

                                characterInfoViewModel
                                    .getEpisodeByIdRickAndMorty(id = fromStringToInt(item))
                                    .onEach {
                                        episode = it
                                    }.launchWhenStarted(lifecycleScope, lifecycle)

                                when(episode){
                                    is Response.Error -> {
                                        Text(text = episode.message ?: "Error")
                                    }
                                    is Response.Loading -> {
                                        FilmListShimmer()
                                    }
                                    is Response.Success -> {
                                        Column(
                                            modifier = Modifier.padding(
                                                vertical = 5.dp,
                                                horizontal = 0.dp
                                            )
                                        ) {

                                            Text(
                                                text = episode.data?.name ?: "",
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight.W500
                                            )

                                            Text(
                                                text = episode.data?.episode ?: "",
                                                modifier = Modifier.padding(5.dp)
                                            )

                                            Text(
                                                text = episode.data?.air_date ?: "",
                                                modifier = Modifier.padding(5.dp)
                                            )

                                            Divider()
                                        }
                                    }
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.height(60.dp))
                            }
                        })
                    }
                }
            }
        }
    )
}