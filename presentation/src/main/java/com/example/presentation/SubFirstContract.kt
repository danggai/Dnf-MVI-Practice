package com.example.presentation

import com.example.presentation.core.UiEffect
import com.example.presentation.core.UiEvent
import com.example.presentation.core.UiState

class SubFirstContract {
    // Events that user performed
    sealed class Event : UiEvent {
//        object GenerateNumber : Event()
    }

    // Ui View States
    data class State(
        val isLoading: Boolean = false,
    ) : UiState

    // Side effects
    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }
}
