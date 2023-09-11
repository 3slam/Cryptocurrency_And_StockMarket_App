package com.example.cryptocurrencyapp.presentation.coinsList

sealed class CoinListEvent {
    data class OnSearchQueryChange(val query: String): CoinListEvent()
}