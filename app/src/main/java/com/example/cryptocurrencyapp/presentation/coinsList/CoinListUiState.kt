package com.example.cryptocurrencyapp.presentation.coinsList

 import com.example.cryptocurrencyapp.domain.entity.Coin



data class CoinListUiState(
    val isLoading: Boolean = false,
    var coins: List<Coin> = emptyList(),
    val error: String = "" ,
    val searchQuery: String = "" ,
)