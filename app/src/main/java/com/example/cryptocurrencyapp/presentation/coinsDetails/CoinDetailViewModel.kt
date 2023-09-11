package com.example.cryptocurrencyapp.presentation.coinsDetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.COIN_ID_PARAMETER
import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.domain.useCases.GetCoinDetailsUseCase
import com.example.cryptocurrencyapp.presentation.coinsList.CoinListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
private val getCoinDetailsUseCase: GetCoinDetailsUseCase ,
    savedStateHandle: SavedStateHandle
):ViewModel(){

    private var _state = MutableStateFlow(CoinDetailUiState())
    val state  = _state.asStateFlow()

    init {
        val coinID = savedStateHandle.get<String>(COIN_ID_PARAMETER) ?: ""
        getCoinDetailById(coinID)

    }
      fun getCoinDetailById(coinId:String) = viewModelScope.launch {
        getCoinDetailsUseCase.invoke(coinId).collect {
            when (it) {
                is UiState.Error -> {
                    _state.value = (CoinDetailUiState(error = it.message))
                    Log.i("coinID - vm" , it.message)
                }
                UiState.Loading -> _state.value = (CoinDetailUiState(isLoading = true))
                is UiState.Success -> _state.value = (CoinDetailUiState(isLoading = false, coinDetail = it.data))
            }
        }

    }
}