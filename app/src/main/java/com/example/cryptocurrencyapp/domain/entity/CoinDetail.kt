package com.example.cryptocurrencyapp.domain.entity

import com.example.cryptocurrencyapp.data.dataSource.remote.coinsResponse.Team


data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<Team>
)

