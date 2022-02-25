package com.example.kinopoisk.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Base64
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.kinopoisk.R
import com.example.kinopoisk.screen.filmTop.viewState.NameTopViewState
import com.example.kinopoisk.utils.viewState.ViewStatePremiere
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

fun <T>Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope){
    lifecycleScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}

class Converters {

    inline fun<reified T> encodeToString(base:T):String{
        return Json.encodeToString(base)
    }

    inline fun<reified T> decodeFromString(string: String):T{
        return Json.decodeFromString(string)
    }

    fun getCurrentTime():String{
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MM-yy hh:mm", Locale.getDefault())
        return formatter.format(time)
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(string: String):String{
        return SimpleDateFormat("dd.MM.yyyy")
            .parseTime(string)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("SimpleDateFormat")
    private fun SimpleDateFormat.parseTime(string: String):String{
        return try {
            val format = SimpleDateFormat("yyyy-MM-dd")
            val time = format.parse(string)
            format(time)
        }catch (e:Exception){
            ""
        }
    }

    fun getNameTop(
        nameTopViewState: NameTopViewState
    ):String{
        return when(nameTopViewState){
            NameTopViewState.TOP_250_BEST_FILMS -> "250 лучших фильмов"
            NameTopViewState.TOP_100_POPULAR_FILMS -> "Популярные фильмы и сериалы"
            NameTopViewState.TOP_AWAIT_FILMS -> "Ожидаемые фильмы"
        }
    }

    fun replaceRange(string: String, int: Int):String{
        if (string.length < int)
            return string
        return string.replaceRange(
            int until string.length,
            "..."
        )
    }

    fun getDatePremiere(
        viewStatePremiere: ViewStatePremiere
    ):String{
        val time = Calendar.getInstance().time
        return when(viewStatePremiere){
            ViewStatePremiere.YEAR -> {
                val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
                formatter.format(time)
            }
            ViewStatePremiere.MONTH -> {
                val formatter = SimpleDateFormat("MM", Locale.getDefault())
                return when(formatter.format(time).toInt()){
                    1-> "JANUARY"
                    2-> "FEBRUARY"
                    3-> "MARCH"
                    4-> "APRIL"
                    5-> "MAY"
                    6-> "JUNE"
                    7-> "JULY"
                    8-> "AUGUST"
                    9-> "SEPTEMBER"
                    10-> "OCTOBER"
                    11-> "NOVEMBER"
                    12-> "DECEMBER"
                    else -> ""
                }
            }
        }
    }

    fun rating(rating: Float): Color {
        if (rating <= 4.9f)
            return Color.Red
        if (rating <= 6.9f || rating == 0f)
            return Color.White
        return Color.Green
    }

    fun toBitmap(string: String, context: Context):Bitmap{
        return try {
            val base = Base64.decode(string, Base64.NO_WRAP)
            BitmapFactory.decodeByteArray(base,0,base.size)
        }catch (e:java.lang.Exception){
            toBitmap(R.drawable.icon, context)
        }
    }

    fun toBitmap(int: Int, context: Context):Bitmap{
        return BitmapFactory.decodeResource(
            context.resources,
            int
        )
    }

    fun fromBitmap(bitmap: Bitmap):String{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream)
        val array = stream.toByteArray()
        return Base64.encodeToString(array, Base64.NO_WRAP)
    }

    @SuppressLint("NewApi")
    @Composable
    fun selectImage(
        bitmap: MutableState<Bitmap>,
        boolean: MutableState<Boolean>,
        context: Context
    ): ManagedActivityResultLauncher<String, Uri?> {
        val select = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()){
            it?.let { uri: Uri ->
                val source = ImageDecoder.createSource(context.applicationContext.contentResolver,uri)
                bitmap.value = ImageDecoder.decodeBitmap(source)

                boolean.value = false
            }
        }
        return select
    }
}