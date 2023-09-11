package com.example.cryptocurrencyapp.presentation.coinsList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

import com.example.cryptocurrencyapp.presentation.coinsList.components.coinListItem
import com.example.cryptocurrencyapp.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun coinListScreen(
    navController: NavHostController,
      viewModel: CoinListViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value
   Column (
       modifier = Modifier.fillMaxSize() ,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {

       OutlinedTextField(
           value = state.searchQuery,
           onValueChange = {
            viewModel.onEvent(CoinListEvent.OnSearchQueryChange(it))
           },
           modifier = Modifier
               .padding(16.dp)
               .fillMaxWidth(),
           placeholder = {
               Text(text = "Search ...")
           },
           maxLines = 1,
           singleLine = true
       )
       if (state.error.isNotBlank()){
           Text(
               text = state.error,
               color = MaterialTheme.colorScheme.error
           )
       }
       if (state.isLoading){
           CircularProgressIndicator()
       }

       LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)) {
           items(state.coins) { coin ->
               coinListItem(coin ,
                   onItemClick = { navController.navigate(Screen.CoinDetails.route + "/${coin.id}")}
               )
           }
       }
   }


    }

