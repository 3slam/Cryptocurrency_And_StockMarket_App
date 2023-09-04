package com.example.cryptocurrencyapp.presentation.coinsList

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.cryptocurrencyapp.presentation.coinsList.components.coinListItem
import com.example.cryptocurrencyapp.presentation.navigation.Screen

@Composable
fun coinListScreen(
    viewModel: CoinListViewModel = hiltViewModel(),
    navController: NavController
) {


    val state = viewModel.state.value

    Box (modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp)){

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (state.error.isNotBlank()){
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { coin ->
                coinListItem(
                    coin ,
                    onItemClick = {
                      navController.navigate( Screen.CoinDetails.passId(coin.id))
                    }
                )
            }
        }
    }

