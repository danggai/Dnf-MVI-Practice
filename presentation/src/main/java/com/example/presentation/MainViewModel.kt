package com.example.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor() :
    ViewModel(), ContainerHost<MainContract.State, MainContract.Effect> {

    override val container = container<MainContract.State, MainContract.Effect>(
        initialState = MainContract.State()
    )

    // Orbit 가이드 상 onEvent를 더 선호
    fun onEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.GenerateNumber -> generateRandomNumber()
            is MainContract.Event.Search -> search(event.server, event.id)
        }
    }

    private fun generateRandomNumber() = intent {
        reduce { state.copy(randomNumber = Random.nextInt(1, 1001)) }
    }

    private fun search(server: String, id: String) = intent {
//        if (state.randomNumber.toString() == guessedNumber) {
//            postSideEffect(MainContract.Effect.ShowToast("정답!"))
//            postSideEffect(MainContract.Effect.StartResultActivity)
//        } else {
//            postSideEffect(MainContract.Effect.ShowToast("땡"))
//        }
    }

    fun updateServer(server: String) = intent {
        reduce { state.copy(server = server) }
    }

    fun updateId(id: String) = intent {
        reduce { state.copy(id = id) }
    }

    fun clearPassword() = intent {
        reduce { state.copy(id = "") }
    }
}
