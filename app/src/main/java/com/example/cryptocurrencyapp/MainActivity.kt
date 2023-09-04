package com.example.cryptocurrencyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.presentation.navigation.setUpNavGraph
import com.example.cryptocurrencyapp.ui.theme.CryptocurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           navController = rememberNavController()
             setUpNavGraph(navHostController = navController)
        }

    }
}



@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
//      lateinit var navController: NavHostController
//    CryptocurrencyAppTheme {
//        navController = rememberNavController()
//        setUpNavGraph(navHostController = navController)
//    }

}