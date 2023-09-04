package com.example.cryptocurrencyapp.presentation.coinsDetails


import com.example.cryptocurrencyapp.domain.entity.CoinDetail

data class CoinDetailUiState (
    val isLoading: Boolean = false,
    var coinDetail: CoinDetail? = null,
    val error: String = ""
)