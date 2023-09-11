@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cryptocurrencyapp.presentation.navigation.DashboardScreenUI

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.presentation.navigation.DashboardNav
import com.example.cryptocurrencyapp.presentation.navigation.DashboardScreen
import com.example.cryptocurrencyapp.presentation.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreenUI(
    navController : NavHostController = rememberNavController()
){
    Scaffold (bottomBar = { BottomNavigationBar (navController) })
    {
        DashboardNav(navController)
    }
}
@Composable
fun BottomNavigationBar(
    navController :NavHostController
) {
    val screens = listOf(DashboardScreen.CoinList, DashboardScreen.CompanyList)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }


    if(bottomBarDestination){
        NavigationBar  {
            screens.forEach { screen ->
                NavigationBarItem(
                    label = {
                        Text(text = screen.title)
                    },
                    icon = {
                        Icon(
                            imageVector =screen.icon,
                            contentDescription = "Navigation Icon"
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,

                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }
                )
            }

        }
    }


  }



