package com.example.cryptocurrencyapp.presentation.navigation

import com.example.cryptocurrencyapp.common.COIN_ID_PARAMETER


sealed class Screen (val rout : String) {
    object CoinList : Screen(rout = "coin_list_screen")

    object CoinDetails  : Screen(rout = "coin_details_screen/{id}"){
        fun passId(id :String) = "coin_details_screen/$id"
    }

}
