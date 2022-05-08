package com.example.feature_web.view

import android.graphics.Bitmap
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature

@Composable
fun WebView(
    baseUrl:String,
    progress:(Int) -> Unit,
    favicon:(Bitmap?) -> Unit,
    url:(String?) -> Unit,
    title:(String?) -> Unit
) {
    AndroidView(modifier = Modifier.fillMaxSize(),factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            settings.databaseEnabled = true

            if(WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)){
                WebSettingsCompat.setForceDark(
                    this.settings,
                    WebSettingsCompat.FORCE_DARK_ON
                )
            }

            Log.e("WebView:Scroll","${this.scrollX}/${this.scrollY}")

            webViewClient = WebViewClient()
            loadUrl(baseUrl)
        }
    }, update = {
        it.apply {
            loadUrl(baseUrl)
            url(this.url)
            favicon(getFavicon())
            progress(this.progress)
            title(this.title)
        }
    })
}