package com.example.feature_sorting.validate

import android.content.Context
import android.widget.Toast

fun ratingSorting(
    min:String,
    max:String,
    context: Context
):Boolean{

    if (min.isEmpty() || max.isEmpty()){
        Toast.makeText(context, "Rating is empty", Toast.LENGTH_SHORT).show()
        return false
    }

    if (min.toInt() > max.toInt()){
        Toast.makeText(context, "min > max", Toast.LENGTH_SHORT).show()
        return false
    }

    if (min.toInt() > 10 || max.toInt() > 10 || min.toInt() == 0){
        Toast.makeText(context, "Rating от 0 до 10", Toast.LENGTH_SHORT).show()
        return false
    }

    return true
}

fun yearSorting(
    min: String,
    max: String,
    context: Context
):Boolean{
    if (min.isEmpty() || max.isEmpty()){
        Toast.makeText(context, "Rating is empty", Toast.LENGTH_SHORT).show()
        return false
    }

    if (min.toInt() > max.toInt()){
        Toast.makeText(context, "min > max", Toast.LENGTH_SHORT).show()
        return false
    }

    if (min.toInt() > 2050 || max.toInt() > 2050 || min.toInt() < 1800){
        Toast.makeText(context, "Rating от 1800 до 2050", Toast.LENGTH_SHORT).show()
        return false
    }

    return true
}