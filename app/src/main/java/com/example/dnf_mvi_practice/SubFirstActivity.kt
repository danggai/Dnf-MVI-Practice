package com.example.dnf_mvi_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.dnf_mvi_practice.ui.SubScreen
import com.example.dnf_mvi_practice.ui.theme.AppTheme

class SubFirstActivity : ComponentActivity() {
    private val viewModel: SubFirstViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(darkTheme = true) {
                SubScreen(viewModel)
            }
        }
    }
}