package com.example.presentation

import com.example.presentation.core.UiEffect
import com.example.presentation.core.UiEvent
import com.example.presentation.core.UiState

class MainContract {
    // Events that user performed
    sealed class Event : UiEvent {
        object GenerateNumber : Event()
        data class Search(val server: String, val id: String) : Event()
    }

    // Ui View States
    data class State(
        val isLoading: Boolean = false,
        val randomNumber: Int? = null,

        val server: String = "",
        val id: String = "",
        val error: String? = null,

        val serverList: List<String> = listOf("카인", "프레이", "카시야스", "안톤", "루크")
    ) : UiState

    // Side effects
    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
        object StartResultActivity : Effect()
    }
}
