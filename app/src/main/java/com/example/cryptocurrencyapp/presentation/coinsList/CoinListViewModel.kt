package com.example.cryptocurrencyapp.presentation.coinsList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.UiState
import com.example.cryptocurrencyapp.domain.entity.Coin
import com.example.cryptocurrencyapp.domain.useCases.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val COIN_LIST = "COIN_LIST"
@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase ,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _state = mutableStateOf(CoinListUiState())
    val state: State<CoinListUiState> = _state

   //    private var _state = MutableStateFlow(CoinListUiState())
   //    val state  = _state.asStateFlow()



    init {
        getAllCoins()
    }

    fun onEvent(event: CoinListEvent) {
        when(event) {
            is CoinListEvent.OnSearchQueryChange -> {
                searchInCoinList( event.query)
            }
        }
    }


    private fun getAllCoins() = viewModelScope.launch {
        getCoinUseCase().collect {
            when (it) {
                is UiState.Error -> _state.value = (CoinListUiState(error = it.message))
                 UiState.Loading -> _state.value = (CoinListUiState(isLoading = true))
                is UiState.Success -> {
                    _state.value = (CoinListUiState(isLoading = false, coins = it.data))
                    savedStateHandle[COIN_LIST] = _state.value.coins
                }
            }
        }
    }

    private fun searchInCoinList(query: String){
       val coinList = savedStateHandle.get<List<Coin>>(COIN_LIST)!!
       val resultAfterSearch = coinList.filter  { it.name.contains(query, ignoreCase = true)}
        _state.value = (CoinListUiState(searchQuery= query , coins = resultAfterSearch))
    }
}