package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.common.toCoinDetails
import com.example.cryptocurrencyapp.common.toCoinList
import com.example.cryptocurrencyapp.data.dataSource.remote.CoinService
import com.example.cryptocurrencyapp.domain.entity.Coin
import com.example.cryptocurrencyapp.domain.entity.CoinDetail
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinService: CoinService
) : CoinRepository{
    override suspend fun getCoins(): Flow<UiState<List<Coin>>> = flow {
        try {

            emit(UiState.Loading)
            val result = coinService.getCoins()
            emit(UiState.Success(toCoinList(result)))

        } catch(e: IOException) {
            emit(UiState.Error(e.message.toString()))
        } catch (e: HttpException){
            emit(UiState.Error(e.message.toString()))
        } catch (e: Exception){
            emit(UiState.Error(e.message.toString()))
        }

    }

    override suspend fun getCoinById(coinId: String): Flow<UiState<CoinDetail>> = flow {
        try {

            emit(UiState.Loading)
            val result = coinService.getCoinById(coinId)
            emit(UiState.Success(toCoinDetails(result)))

        } catch(e: IOException) {
            emit(UiState.Error(e.message.toString()))
        } catch (e: HttpException){
            emit(UiState.Error(e.message.toString()))
        } catch (e: Exception){
            emit(UiState.Error(e.message.toString()))
        }
    }


}