package com.example.dnf_mvi_practice.utils

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