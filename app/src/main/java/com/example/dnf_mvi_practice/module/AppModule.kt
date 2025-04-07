package com.example.dnf_mvi_practice.module

import android.content.Context
import com.example.data.repository.ResourceProviderRepositoryImpl
import com.example.dnf_mvi_practice.Application
import com.example.domain.repository.ResourceProviderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(): Application {
        return Application()
    }

    @Singleton
    @Provides
    fun provideResourceProvider(
        @ApplicationContext context: Context
    ): ResourceProviderRepository = ResourceProviderRepositoryImpl(context)
}