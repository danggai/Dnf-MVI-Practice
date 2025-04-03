package com.example.data.network.servers.repository

import com.example.data.network.servers.remote.api.ServerApi
import com.example.domain.core.ApiResult
import com.example.domain.network.servers.entity.ServerList
import com.example.domain.network.servers.repository.ServerRepository
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi,
    private val ioDispatcher: CoroutineDispatcher
) : ServerRepository {
    override suspend fun list(
        onStart: () -> Unit,
        onComplete: () -> Unit
    ) = flow<ApiResult<ServerList>> {
        val response = serverApi.list()

        response.suspendOnSuccess {
            emit(this.response.body()?.let {
                ApiResult.Success<ServerList>(
                    this.response.code(),
                    it
                )
            } ?: ApiResult.Null())
        }.suspendOnError {
            emit(
                ApiResult.Failure(
                    this.response.code(),
                    this.response.message()
                )
            )
        }.suspendOnException {
            emit(
                ApiResult.Error(
                    this.exception
                )
            )
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}