package com.example.kinopoisk.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.LifecycleCoroutineScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.kinopoisk.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T>Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope){
    lifecycleScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}

class Converters {

    fun toBitmap(
        int: Int,
        context: Context
    ):Bitmap{
        return BitmapFactory.decodeResource(
            context.resources,
            int
        )
    }

    suspend fun bitmapCoil(
        url:String,
        context: Context
    ): Bitmap {
        val loading = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .error(R.drawable.image)
            .build()
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}