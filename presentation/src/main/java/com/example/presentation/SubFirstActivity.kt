package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.presentation.ui.SubScreen
import com.example.presentation.ui.theme.AppTheme

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