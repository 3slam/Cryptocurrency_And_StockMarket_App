package com.example.cryptocurrencyapp.presentation.coinsList

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.domain.useCases.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private var _state = mutableStateOf(CoinListUiState())
    val state: State<CoinListUiState> = _state


    init {
        getAllCoins()
    }

    private fun getAllCoins() = viewModelScope.launch {
        getCoinUseCase().collect {
            when (it) {
                is UiState.Error -> _state.value = (CoinListUiState(error = it.message))
                UiState.Loading -> _state.value = (CoinListUiState(isLoading = true))
                is UiState.Success ->  _state.value = (CoinListUiState(isLoading = false, coins = it.data))
            }
        }
    }
}