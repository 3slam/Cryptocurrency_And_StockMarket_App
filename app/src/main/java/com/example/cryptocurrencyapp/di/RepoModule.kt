package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.data.dataSource.remote.CoinService
import com.example.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(coinService: CoinService): CoinRepository {
        return CoinRepositoryImpl(coinService)
    }
}