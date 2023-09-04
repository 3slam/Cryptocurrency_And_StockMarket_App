package com.example.cryptocurrencyapp.presentation.coinsDetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.domain.useCases.GetCoinDetailsUseCase
import com.example.cryptocurrencyapp.presentation.coinsList.CoinListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
private val getCoinDetailsUseCase: GetCoinDetailsUseCase ,
savedStateHandle: SavedStateHandle
):ViewModel(){

    private var _state = mutableStateOf(CoinDetailUiState())
    val state: State<CoinDetailUiState> = _state


      fun getCoinDetailById(coinId:String) = viewModelScope.launch {
        getCoinDetailsUseCase.invoke(coinId).collect {
            when (it) {
                is UiState.Error -> _state.value= (CoinDetailUiState(error = it.message))
                UiState.Loading -> _state.value = (CoinDetailUiState(isLoading = true))
                is UiState.Success ->  _state.value = (CoinDetailUiState(isLoading = false, coinDetail = it.data))
            }
        }
          Log.i("Mia vm  " ,coinId )
    }
}