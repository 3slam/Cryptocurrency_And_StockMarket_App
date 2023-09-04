package com.example.cryptocurrencyapp.common

import com.example.cryptocurrencyapp.data.dataSource.remote.coinsResponse.CoinDetailDto
import com.example.cryptocurrencyapp.data.dataSource.remote.coinsResponse.CoinDto
import com.example.cryptocurrencyapp.domain.entity.Coin
import com.example.cryptocurrencyapp.domain.entity.CoinDetail
import com.example.cryptocurrencyapp.presentation.navigation.Screen

fun toCoin(coinDto: CoinDto): Coin {
    return Coin(
        id = coinDto.id,
        isActive = coinDto.is_active,
        name = coinDto.name,
        rank = coinDto.rank,
    )
}


fun toCoinList(coinDtoList: List<CoinDto>): List<Coin> {
    return coinDtoList.map{
        toCoin(it)
    }
}

fun toCoinDetails(coinDetailsDto: CoinDetailDto): CoinDetail {
    return  CoinDetail(
        coinId = coinDetailsDto.id ,
        name =  coinDetailsDto.name ,
        description =  coinDetailsDto.description,
        symbol =coinDetailsDto.symbol    ,
        rank =  coinDetailsDto.rank ,
        isActive = coinDetailsDto.is_active ,
        tags = coinDetailsDto.tags.map { it.toString() } ,
        team = coinDetailsDto.team
    )
}


