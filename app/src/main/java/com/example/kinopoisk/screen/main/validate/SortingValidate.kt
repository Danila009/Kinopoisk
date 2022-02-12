package com.example.kinopoisk.screen.main.validate

import android.content.Context
import android.widget.Toast

class SortingValidate {

    fun sorting(
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

        if (min.length > 10 || max.length > 10 || min.toInt() == 0){
            Toast.makeText(context, "Rating от 0 до 10", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}