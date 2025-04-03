package com.example.domain.network.servers.entity

data class ServerList(
    val rows: List<Server>
) {
    companion object {
        val EMPTY = ServerList(listOf())
    }
}

data class Server(
    val serverId: String,
    val serverName: String
) {
    companion object {
        val EMPTY = Server("", "서버")
    }
}