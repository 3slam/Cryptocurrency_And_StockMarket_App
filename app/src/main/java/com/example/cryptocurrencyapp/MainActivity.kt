@file:OptIn(ExperimentalFoundationApi::class)

package com.example.cryptocurrencyapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.presentation.coinsDetails.coinDetailScreen
import com.example.cryptocurrencyapp.presentation.companyInfo.companyInfoScreen
import com.example.cryptocurrencyapp.presentation.companyList.companyListScreen
import com.example.cryptocurrencyapp.presentation.navigation.setUpNavGraph
import com.example.cryptocurrencyapp.presentation.onBoardingScreen.OnBoardingPage
import com.example.cryptocurrencyapp.presentation.onBoardingScreen.PagerScreen
 import com.example.cryptocurrencyapp.ui.theme.CryptocurrencyAppTheme

import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalLayoutApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CryptocurrencyAppTheme {
                setUpNavGraph(navController = rememberNavController())
            }
        }
    }
}



//@Preview(showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    CryptocurrencyAppTheme {
//     //   setUpNavGraph(navController = rememberNavController())
//    }
//   }
//
