package com.example.cryptocurrencyapp.domain.useCases

import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import com.example.cryptocurrencyapp.domain.repository.CompanyRepository
import javax.inject.Inject


class GetCompaniesUseCase @Inject constructor(
    private val repository: CompanyRepository
) {
    suspend operator fun invoke() = repository.getCompanies()
}