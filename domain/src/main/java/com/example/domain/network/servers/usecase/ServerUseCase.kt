package com.example.domain.network.servers.usecase

import com.example.domain.core.ApiResult
import com.example.domain.network.servers.entity.ServerList
import com.example.domain.network.servers.repository.ServerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServerUseCase @Inject constructor(
    private val serverRepository: ServerRepository
) {
    suspend fun list(
        onStart: () -> Unit = {},
        onComplete: () -> Unit = {}
    ): Flow<ApiResult<ServerList>> =
        serverRepository.list(
            onStart = onStart,
            onComplete = onComplete
        )
}