package com.example.cryptocurrencyapp.presentation.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cryptocurrencyapp.common.COIN_ID_PARAMETER
import com.example.cryptocurrencyapp.presentation.coinsDetails.coinDetailScreen
import com.example.cryptocurrencyapp.presentation.coinsList.coinListScreen

@Composable
fun setUpNavGraph(
  navHostController: NavHostController
){
  NavHost(navController = navHostController ,
      startDestination = Screen.CoinList.rout
      ){

      composable(
          route = Screen.CoinList.rout ,
      ){
          coinListScreen(navController = navHostController)

      }


      composable(
          route = Screen.CoinDetails.rout ,
          arguments = listOf(
              navArgument("id"){
                  type = NavType.StringType
              }
          )
      ){
          val coinId = it.arguments?.getString("id")
          Log.i("Mia vm nav" ,coinId.toString())
          coinDetailScreen(coinID = coinId, navController = navHostController )
      }

  }
}