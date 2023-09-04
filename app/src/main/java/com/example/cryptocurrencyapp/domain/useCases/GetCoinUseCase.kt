package com.example.cryptocurrencyapp.domain.useCases

import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.getCoins()
}