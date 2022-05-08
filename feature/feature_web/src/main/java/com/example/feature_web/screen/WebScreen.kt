package com.example.feature_web.screen

import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.webkit.WebIconDatabase
import android.webkit.WebViewDatabase
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.core_ui.ui.theme.secondaryBackground
import com.example.core_utils.common.replaceRange
import com.example.feature_web.view.WebView

@Composable
fun WebScreen(
    navController: NavController,
    webUrl:String
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        WebViewDatabase.getInstance(context)
//        WebIconDatabase.getInstance().open(context.getDir("icons", MODE_PRIVATE).path)
    })

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
            WebView(
                baseUrl = webUrl,
                title = {
                    Log.e("WebView:Title", it.toString())
                },
                progress = {
                    Log.e("WebView:Progress", it.toString())
                }, favicon = {
                    Log.e("WebView:Favicon", it.toString())
                }, url = {
                    Log.e("WebView:Url", it.toString())
                }
            )
        }
    )
}