package com.example.cryptocurrencyapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cryptocurrencyapp.presentation.onBoardingScreen.onboardingScreen

fun NavGraphBuilder.onBoardingNav(
    navController: NavHostController
){
    navigation(
         startDestination = Screen.OnBoarding.route ,
        route = GraphRout.ON_BOARDING
    ){

        composable(
            Screen.OnBoarding.route
        ){
            onboardingScreen(navController)
        }

    }
}