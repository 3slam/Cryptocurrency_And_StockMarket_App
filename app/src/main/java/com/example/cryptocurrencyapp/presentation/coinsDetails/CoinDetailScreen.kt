package com.example.cryptocurrencyapp.presentation.coinsDetails

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun coinDetailScreen (
    coinID :String? ,
    viewModel: CoinDetailViewModel = hiltViewModel(),
    navController: NavController
){

    viewModel.getCoinDetailById(coinID!!)
    val state = viewModel.state.value

    Box (
        modifier = Modifier.fillMaxSize() ,
        contentAlignment =  Alignment.Center
    ){
     Text(text = state.coinDetail.toString() , modifier = Modifier.clickable {
         navController.popBackStack()
     })
    }

}