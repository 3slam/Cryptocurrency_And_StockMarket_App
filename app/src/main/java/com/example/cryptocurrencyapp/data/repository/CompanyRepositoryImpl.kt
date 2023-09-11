package com.example.cryptocurrencyapp.data.repository

import android.content.Context
import android.util.Log
import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.data.dataSource.csv.CompanyIntradayInfoParse
import com.example.cryptocurrencyapp.data.dataSource.csv.CompanyParse
import com.example.cryptocurrencyapp.data.dataSource.remote.CompanyService
import com.example.cryptocurrencyapp.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.cryptocurrencyapp.R

class CompanyRepositoryImpl @Inject constructor(
    private val companyService: CompanyService ,
    private  val appContext: Context
): CompanyRepository {

    override suspend fun getCompanies() = flow {

        try {
            emit(UiState.Loading)
            val resultFromApi =  appContext.resources.openRawResource( R.raw.company_list)
            val resultFromParser = CompanyParse.parse(resultFromApi)
            emit(UiState.Success(resultFromParser))
        }catch (e:Exception){
            emit(UiState.Error(e.message.toString()))
        }
    }

    override suspend fun getCompanyInfo(symbol: String) = flow {
        try {
            emit(UiState.Loading)
            val resultFromApi = companyService.getLCompanyInfo(symbol)
            emit(UiState.Success(resultFromApi))
        }catch (e:Exception){
            emit(UiState.Error(e.message.toString()))
        }

    }

    override suspend fun getCompanyIntradayChartInfo(symbol: String )= flow {
        try {
            emit(UiState.Loading)
            val resultFromApi =  companyService.getCompanyIntradayInfo(symbol)
            val resultFromParser = CompanyIntradayInfoParse.parse(resultFromApi.byteStream())
            emit(UiState.Success(resultFromParser))
        }catch (e:Exception){
            emit(UiState.Error(e.message.toString()))
        }

    }
}