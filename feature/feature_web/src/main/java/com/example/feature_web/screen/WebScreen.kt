package com.example.feature_web.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.replaceRange
import com.example.feature_web.view.WebView

@Composable
fun WebScreen(
    navController: NavController,
    webUrl:String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = replaceRange(
                        webUrl,
                        50
                    )
                ) }, navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            WebView(url = webUrl)
        }
    )
}