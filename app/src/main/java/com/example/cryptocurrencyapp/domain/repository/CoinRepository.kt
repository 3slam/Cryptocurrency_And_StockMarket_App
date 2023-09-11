package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.domain.entity.*
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(): Flow<UiState<List<Coin>>>

    suspend fun getCoinById(coinId: String): Flow<UiState<CoinDetail>>

}