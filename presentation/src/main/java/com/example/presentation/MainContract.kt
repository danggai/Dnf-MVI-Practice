package com.example.presentation

import com.example.presentation.core.UiEffect
import com.example.presentation.core.UiEvent
import com.example.presentation.core.UiState

class MainContract {
    // Events that user performed
    sealed class Event : UiEvent {
        object GenerateNumber : Event()
        data class GuessNumber(val number: String) : Event()
    }

    // Ui View States
    data class State(
        val isLoading: Boolean = false,
        val randomNumber: Int? = null,

        val password: String = "",
        val error: String? = null
    ) : UiState

    // Side effects
    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
        object StartSubActivity : Effect()
    }
}
