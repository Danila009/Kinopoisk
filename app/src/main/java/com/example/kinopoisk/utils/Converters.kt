package com.example.kinopoisk.utils

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.kinopoisk.utils.viewState.ViewStatePremiere
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*

fun <T>Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope){
    lifecycleScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}

class Converters {

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

}