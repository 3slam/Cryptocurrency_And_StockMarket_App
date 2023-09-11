package com.example.cryptocurrencyapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cryptocurrencyapp.common.COIN_ID_PARAMETER


sealed class Screen (val route : String) {
    object OnBoarding: Screen(route = "OnBoarding")

    object CoinDetails  : Screen(route = "coin_details_screen")

    object CompanyInfo: Screen(route = "CompanyInfo")

}

sealed class  DashboardScreen(
    val route: String ,
    val title :String ,
    val icon  : ImageVector
) {

    object CoinList : DashboardScreen(
        route = "coin_list_screen" ,
        title = "Coins" ,
        icon = Icons.Outlined.List
    )


    object CompanyList : DashboardScreen(
        route = "Companies_list_screen" ,
        title = "Companies" ,
        icon = Icons.Outlined.Home
    )


}
