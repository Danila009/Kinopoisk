package com.example.core_ui.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun BaseBackdropScaffold(
    headerHeight: Dp = BackdropScaffoldDefaults.HeaderHeight,
    peekHeight: Dp = 300.dp,
    backLayerContent: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit,
    scaffoldState: BackdropScaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed),
    gesturesEnabled:Boolean = scaffoldState.isConcealed,
) {
    BackdropScaffold(
        headerHeight = headerHeight,
        scaffoldState = scaffoldState,
        gesturesEnabled = gesturesEnabled,
        peekHeight = peekHeight,
        backLayerBackgroundColor = Gray,
        appBar = { /*TODO*/ },
        backLayerContent = backLayerContent,
        frontLayerContent = frontLayerContent
    )
}