package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.ApiResult
import com.example.domain.network.servers.entity.Server
import com.example.domain.network.servers.usecase.ServerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val server: ServerUseCase
) :
    ViewModel(), ContainerHost<MainContract.State, MainContract.Effect> {

    init {
        getServerList()
    }

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

    fun getServerList() {
        viewModelScope.launch {
            server.list().collect {
                intent {
                    when (it) {
                        is ApiResult.Success -> {
                            reduce { state.copy(serverList = it.data.rows) }
                        }

                        is ApiResult.Failure,
                        is ApiResult.Error,
                        is ApiResult.Null -> {

                        }
                    }
                }
            }
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

    fun updateServer(server: Server) = intent {
        reduce { state.copy(server = server) }
    }

    fun updateId(id: String) = intent {
        reduce { state.copy(id = id) }
    }

    fun clearPassword() = intent {
        reduce { state.copy(id = "") }
    }
}
