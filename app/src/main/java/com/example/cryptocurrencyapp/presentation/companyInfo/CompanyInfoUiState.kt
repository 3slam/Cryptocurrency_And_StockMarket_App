package com.example.cryptocurrencyapp.presentation.companyInfo

import com.example.cryptocurrencyapp.domain.entity.Company
import com.example.cryptocurrencyapp.domain.entity.CompanyInfo
import com.example.cryptocurrencyapp.domain.entity.CompanyIntradayChartInfo


data class CompanyInfoUiState  (
    val isLoading: Boolean = false,
    var companyInfo: CompanyInfo? =null,
    val error: String = "",
)

data class CompanyChartUiState  (
    val isLoading: Boolean = false,
    var companyIntradayChartInfo: List<CompanyIntradayChartInfo> = emptyList(),
    val error: String = "",
)