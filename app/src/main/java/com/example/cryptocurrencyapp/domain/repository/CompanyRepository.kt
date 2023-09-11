package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.domain.entity.Coin
import com.example.cryptocurrencyapp.domain.entity.Company
import com.example.cryptocurrencyapp.domain.entity.CompanyInfo
import com.example.cryptocurrencyapp.domain.entity.CompanyIntradayChartInfo
import kotlinx.coroutines.flow.Flow

interface CompanyRepository {

    suspend fun getCompanies(): Flow<UiState<List<Company>>>

    suspend fun getCompanyInfo(symbol :String): Flow<UiState<CompanyInfo>>

    suspend fun getCompanyIntradayChartInfo(symbol :String): Flow<UiState<List<CompanyIntradayChartInfo>>>
}