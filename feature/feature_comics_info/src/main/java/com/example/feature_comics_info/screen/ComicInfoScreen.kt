package com.example.feature_comics_info.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_ui.ui.theme.primaryBackground
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.state.ComicsState
import com.example.feature_comics_info.view.ComicMarvel
import com.example.feature_comics_info.viewModel.ComicInfoViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
internal fun ComicInfoScreen(
    viewModel: ComicInfoViewModel,
    navController: NavController,
    comicsState: ComicsState,
    comicId:Int
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = primaryBackground,
                elevation = 8.dp,
                title = {
                    Text(text = "Comics Information")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null, tint = secondaryBackground
                        )
                    }
                }
            )
        },
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = primaryBackground
            ) {
                when(comicsState){
                    ComicsState.MARVEL -> ComicMarvel(
                        viewModel = viewModel,
                        comicId = comicId
                    )
                }
            }
        }
    )
}