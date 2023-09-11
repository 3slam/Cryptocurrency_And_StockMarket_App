package com.example.cryptocurrencyapp.di

import android.content.Context
import com.example.cryptocurrencyapp.data.dataSource.remote.CoinService
import com.example.cryptocurrencyapp.data.dataSource.remote.CompanyService
import com.example.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencyapp.data.repository.CompanyRepositoryImpl
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import com.example.cryptocurrencyapp.domain.repository.CompanyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideCoinRepository(coinService: CoinService): CoinRepository {
        return CoinRepositoryImpl(coinService)
    }

    @Provides
    fun provideCompanyRepository(@ApplicationContext appContext: Context, companyService: CompanyService ): CompanyRepository {
        return CompanyRepositoryImpl(companyService , appContext)
    }

}