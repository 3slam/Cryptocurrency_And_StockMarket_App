package com.example.cryptocurrencyapp.presentation.companyInfo

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.COIN_ID_PARAMETER
import com.example.cryptocurrencyapp.common.COMPANY_ID_PARAMETER
import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.domain.useCases.GetCompaniesUseCase
import com.example.cryptocurrencyapp.domain.useCases.GetCompanyInfoUseCase
import com.example.cryptocurrencyapp.domain.useCases.GetCompanyIntradayChartInfoUseCase
import com.example.cryptocurrencyapp.presentation.companyList.CompanyListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val getCompanyInfoUseCase: GetCompanyInfoUseCase ,
    private val getCompanyIntradayChartInfoUseCase: GetCompanyIntradayChartInfoUseCase ,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private var _companyInfoState = MutableStateFlow(CompanyInfoUiState())
    val  companyInfoState  = _companyInfoState.asStateFlow()

    private var _companyChartState = MutableStateFlow(CompanyChartUiState())
    val  companyChartState  = _companyChartState.asStateFlow()

    init {
        val companySymbol = savedStateHandle.get<String>(COMPANY_ID_PARAMETER) ?: ""
        getCompanyInfo(companySymbol)
        getCompanyChart(companySymbol)
     }


    private fun getCompanyChart(companySymbol :String) = viewModelScope.launch {

        getCompanyIntradayChartInfoUseCase(companySymbol).collect {
            when (it) {
                is UiState.Error -> _companyChartState.value =  CompanyChartUiState(error =  it.message)
                UiState.Loading -> _companyChartState.value = CompanyChartUiState(isLoading = true)
                is UiState.Success -> _companyChartState.value = CompanyChartUiState(isLoading = false, companyIntradayChartInfo = it.data)
            }
        }
    }

    private fun getCompanyInfo(companySymbol :String) =  viewModelScope.launch {

        getCompanyInfoUseCase(companySymbol).collect {
            when (it) {
                is UiState.Error -> _companyInfoState.value = CompanyInfoUiState(error = it.message)
                UiState.Loading -> _companyInfoState.value = (CompanyInfoUiState(isLoading = true))
                is UiState.Success -> _companyInfoState.value =
                    (CompanyInfoUiState(isLoading = false, companyInfo = it.data))
            }
        }
      }

}


