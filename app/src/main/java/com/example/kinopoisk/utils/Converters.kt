package com.example.kinopoisk.utils

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.core_utils.state.NameTopState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat

fun <T>Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope){
    lifecycleScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}

class Converters {

    inline fun<reified T> decodeFromString(string: String):T{
        return Json.decodeFromString(string)
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
        nameTopViewState: NameTopState
    ):String{
        return when(nameTopViewState){
            NameTopState.TOP_250_BEST_FILMS -> "250 лучших фильмов"
            NameTopState.TOP_100_POPULAR_FILMS -> "Популярные фильмы и сериалы"
            NameTopState.TOP_AWAIT_FILMS -> "Ожидаемые фильмы"
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

    fun rating(rating: Float): Color {
        if (rating <= 4.9f)
            return Color.Red
        if (rating <= 6.9f || rating == 0f)
            return Color.White
        return Color.Green
    }

}