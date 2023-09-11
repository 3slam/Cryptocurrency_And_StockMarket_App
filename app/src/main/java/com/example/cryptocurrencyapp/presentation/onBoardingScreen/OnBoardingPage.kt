package com.example.cryptocurrencyapp.presentation.onBoardingScreen


import androidx.annotation.DrawableRes
import com.example.cryptocurrencyapp.R


sealed class OnBoardingPage(

    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.raw.coin_anim,
        title = "Get the updates of Cryptocurrency",
        description = "Cryptocurrency is a digital or virtual form of currency that uses it."
    )

    object Second : OnBoardingPage(
        image = R.raw.stock_anim,
        title = "Get the latest updates of companies",
        description = "A stock company, also known as a publicly traded company."
    )


}