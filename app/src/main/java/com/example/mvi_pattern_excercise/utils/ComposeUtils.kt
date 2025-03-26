package com.example.mvi_pattern_excercise.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

object ComposeUtils {
    fun Context.getActivity(): ComponentActivity? = when (this) {
        is ComponentActivity -> this
        is ContextWrapper -> baseContext.getActivity()
        else -> null
    }
}