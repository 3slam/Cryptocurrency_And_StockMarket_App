package com.example.cryptocurrencyapp.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptocurrencyapp.presentation.navigation.DashboardScreenUI.DashboardScreenUI

@Composable
fun setUpNavGraph(
  navController: NavHostController
){

  NavHost(
    navController = navController ,
    startDestination = GraphRout.ON_BOARDING ,
    route =  GraphRout.ROOT
  ){
    onBoardingNav(navController = navController)

     composable(route = GraphRout.DASHBOARD){
      DashboardScreenUI()
     }
  }

}