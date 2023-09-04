package com.example.cryptocurrencyapp.domain.useCases

import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinDetailsUseCase  @Inject constructor(
    private val repository: CoinRepository
) {
    suspend  fun invoke(coinId:String) = repository.getCoinById(coinId)
}