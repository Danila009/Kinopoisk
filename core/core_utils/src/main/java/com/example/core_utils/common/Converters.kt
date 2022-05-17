package com.example.core_utils.common

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core_utils.state.StatePremiere
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope, lifecycle: Lifecycle){
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
            this@launchWhenStarted.collect()
        }
    }
}

inline fun<reified T> encodeToString(
    base:T
):String{
    val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }
    return json.encodeToString(base)
}

inline fun<reified T> decodeFromString(
    string: String
):T{
    val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }
    return json.decodeFromString(string)
}

fun getDatePremiere(
    viewStatePremiere: StatePremiere
):String{
    val time = Calendar.getInstance().time
    return when(viewStatePremiere){
        StatePremiere.YEAR -> {
            val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
            formatter.format(time)
        }
        StatePremiere.MONTH -> {
            val formatter = SimpleDateFormat("MMMM", Locale.getDefault())
            formatter.format(time).uppercase()
        }
    }
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

fun replaceRange(string: String, int: Int):String{
    if (string.length < int)
        return string
    return string.replaceRange(
        int until string.length,
        "..."
    )
}

fun String.parseHtml():String{
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(
            this,
            Html.FROM_HTML_MODE_LEGACY
        ).toString()
    }else{
        Html.fromHtml(this).toString()
    }
}

fun getCurrentTime():String{
    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd-MM-yy hh:mm", Locale.getDefault())
    return formatter.format(time)
}

fun getDate():String{
    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    return formatter.format(time)
}

fun getDate(
    year:Int = 0,
    month:Int = 0,
    day:Int = 0,
    hour:Int = 0,
    minute:Int = 0,
    second:Int = 0,
    millisecond:Int = 0
):Date{
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.DAY_OF_MONTH, day)
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, minute)
    calendar.set(Calendar.SECOND, second)
    calendar.set(Calendar.MILLISECOND, millisecond)
    return calendar.time
}

fun rating(rating: Float): Color {
    if (rating <= 4.9f)
        return Color.Red
    if (rating <= 6.9f || rating == 0f)
        return Color.White
    return Color.Green
}
