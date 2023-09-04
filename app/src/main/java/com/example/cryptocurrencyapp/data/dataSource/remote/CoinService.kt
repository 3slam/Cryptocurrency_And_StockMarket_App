package com.example.cryptocurrencyapp.data.dataSource.remote

import com.example.cryptocurrencyapp.data.dataSource.remote.coinsResponse.CoinDetailDto
import com.example.cryptocurrencyapp.data.dataSource.remote.coinsResponse.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

     @GET("/v1/coins")
     suspend fun getCoins(): List<CoinDto>

     @GET("/v1/coins/{coinId}")
     suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}