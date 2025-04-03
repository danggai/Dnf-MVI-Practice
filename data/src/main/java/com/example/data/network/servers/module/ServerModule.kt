package com.example.data.network.servers.module

import com.example.data.module.NetworkModule
import com.example.data.network.servers.remote.api.ServerApi
import com.example.data.network.servers.repository.ServerRepositoryImpl
import com.example.domain.network.servers.repository.ServerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ServerModule {
    @Singleton
    @Provides
    fun provideServerApi(retrofit: Retrofit): ServerApi {
        return retrofit.create(ServerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideServerRepository(
        repositoryImpl: ServerRepositoryImpl
    ): ServerRepository = repositoryImpl
}