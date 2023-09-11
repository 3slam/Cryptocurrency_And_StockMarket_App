package com.example.cryptocurrencyapp.presentation.companyList

sealed class CompanyListEvent {
    data class OnSearchQueryChange(val query: String): CompanyListEvent()
}