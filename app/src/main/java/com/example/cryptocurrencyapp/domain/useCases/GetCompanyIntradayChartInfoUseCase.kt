package com.example.cryptocurrencyapp.domain.useCases

import com.example.cryptocurrencyapp.domain.repository.CompanyRepository
import javax.inject.Inject


class GetCompanyIntradayChartInfoUseCase @Inject constructor(
    private val repository: CompanyRepository
) {
    suspend operator fun invoke(symbol:String) = repository.getCompanyIntradayChartInfo(symbol)
}