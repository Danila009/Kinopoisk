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
import java.lang.Exception

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
        return try {
            BitmapFactory.decodeResource(
                context.resources,
                int
            )
        }catch (e:Exception){
            Converters().toBitmap(R.drawable.image, context)
        }
    }

    suspend fun bitmapCoil(
        url:String,
        context: Context
    ): Bitmap {
        return try {
            val loading = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(url)
                .error(R.drawable.image)
                .build()
            val result = (loading.execute(request) as SuccessResult).drawable
            (result as BitmapDrawable).bitmap
        }catch (e:Exception){
            Converters().toBitmap(R.drawable.image, context)
        }
    }
}