package com.example.data.network.servers.remote.api

import com.example.domain.network.servers.entity.ServerList
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ServerApi {
    @Headers(
        "api-name: dfServers",
        "etag: W/\"179-jYBxRyHcM1oik9U2mBADkkPmrEo\"",
    )
    @GET("/df/servers")
    suspend fun list(): ApiResponse<ServerList>
}