package com.example.cryptocurrencyapp.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cryptocurrencyapp.common.COIN_ID_PARAMETER
import com.example.cryptocurrencyapp.common.COMPANY_ID_PARAMETER
import com.example.cryptocurrencyapp.presentation.coinsDetails.CoinDetailViewModel
import com.example.cryptocurrencyapp.presentation.coinsDetails.coinDetailScreen
import com.example.cryptocurrencyapp.presentation.coinsList.coinListScreen
import com.example.cryptocurrencyapp.presentation.companyInfo.companyInfoScreen
import com.example.cryptocurrencyapp.presentation.companyList.companyListScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun DashboardNav(
    navController: NavHostController
){
    NavHost(
        navController = navController ,
        startDestination = DashboardScreen.CoinList.route ,
        route = GraphRout.DASHBOARD
    ){

        composable(
            route =DashboardScreen.CoinList.route
        ){
            coinListScreen(navController)
        }
        composable(
            route = DashboardScreen.CompanyList.route
        ){
            companyListScreen(navController)
        }

         coinDetail(navController)
         companyDetail(navController)
    }

}

fun NavGraphBuilder.coinDetail(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.CoinDetails.route ,
        route = GraphRout.COIN_DETAILS
    ){

        composable(
          route=  Screen.CoinDetails.route + "/{$COIN_ID_PARAMETER}" ,
        ){
            coinDetailScreen( navController)
        }

    }
}

fun NavGraphBuilder.companyDetail(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.CompanyInfo.route ,
        route = GraphRout.COMPANY_DETAILS
    ){

        composable(
            Screen.CompanyInfo.route + "/{$COMPANY_ID_PARAMETER}"
        ){
            companyInfoScreen(navController)
        }

    }
}

