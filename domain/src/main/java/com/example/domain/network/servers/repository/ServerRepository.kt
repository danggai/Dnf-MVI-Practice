package com.example.domain.network.servers.repository

import com.example.domain.core.ApiResult
import com.example.domain.network.servers.entity.ServerList
import danggai.domain.core.Repository
import kotlinx.coroutines.flow.Flow


interface ServerRepository : Repository {
    suspend fun list(
        onStart: () -> Unit,
        onComplete: () -> Unit
    ): Flow<ApiResult<ServerList>>
}