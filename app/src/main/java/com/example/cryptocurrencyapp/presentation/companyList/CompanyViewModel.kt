package com.example.cryptocurrencyapp.presentation.companyList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.domain.entity.Company
import com.example.cryptocurrencyapp.domain.useCases.GetCompaniesUseCase
import com.example.cryptocurrencyapp.presentation.coinsList.CoinListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


const val COMPANY_LIST = "COMPANY_LIST"

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val getCompaniesUseCase: GetCompaniesUseCase ,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private var _state = MutableStateFlow(CompanyListUiState())
    val state  = _state.asStateFlow()



    init {
        getCompanies()
    }

    fun onEvent(event: CompanyListEvent) {
        when(event) {
           is CompanyListEvent.OnSearchQueryChange -> searchInCompanyList( event.query)
        }
    }

    private fun  getCompanies() = viewModelScope.launch {
        getCompaniesUseCase().collect {
            when (it) {
                is UiState.Error -> {
                    _state.value = (CompanyListUiState(error = it.message))
                    Log.i("CompanyListUiState vm" ,it.message)
                }
                UiState.Loading -> _state.value = (CompanyListUiState(isLoading = true))
                is UiState.Success -> {
                    _state.value = (CompanyListUiState(isLoading = false, companies = it.data))
                    Log.i("CompanyListUiState vm" , it.data.toString())
                     savedStateHandle[COMPANY_LIST] = _state.value.companies
                }
            }
        }
    }

    private fun searchInCompanyList(query: String){
        val companyList = savedStateHandle.get<List<Company>>(COMPANY_LIST)!!
        val resultAfterSearch = companyList.filter  { it.symbol.contains(query , ignoreCase = true)}
        _state.value = (CompanyListUiState(searchQuery= query , companies = resultAfterSearch))
    }
}