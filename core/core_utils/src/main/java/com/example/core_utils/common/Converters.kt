package com.example.core_utils.common

import android.annotation.SuppressLint
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

inline fun<reified T> encodeToString(base:T):String{
    return Json.encodeToString(base)
}

inline fun<reified T> decodeFromString(string: String):T{
    return Json.decodeFromString(string)
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