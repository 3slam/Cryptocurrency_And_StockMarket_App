package com.example.cryptocurrencyapp.presentation.companyList

 import com.example.cryptocurrencyapp.domain.entity.Company

data class CompanyListUiState  (
    val isLoading: Boolean = false,
    var companies: List<Company> = emptyList(),
    val error: String = "" ,
    val searchQuery: String = ""
)