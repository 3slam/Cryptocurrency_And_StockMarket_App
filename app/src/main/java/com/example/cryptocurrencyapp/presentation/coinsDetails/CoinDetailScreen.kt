@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package com.example.cryptocurrencyapp.presentation.coinsDetails

import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.cryptocurrencyapp.R
import kotlinx.coroutines.runBlocking


@Composable
fun coinDetailScreen (
    navController: NavHostController ,
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {


        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

    }


    if (state.coinDetail != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally ,

        ) {
            item {


                Card(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onTertiary),
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = state.coinDetail!!.name.plus(" - ${state.coinDetail!!.symbol}"),
                        fontStyle = FontStyle.Italic,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(10.dp)
                        )
                }

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )

                Card(
                    border = BorderStroke(1.dp,
                        if (state.coinDetail!!.isActive) { Color.Green } else { Color.Red }),
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text =
                    if (state.coinDetail!!.isActive) {
                        "Active"
                    } else {
                        "Not Active"
                    },
                        modifier = Modifier.padding(10.dp))
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = state.coinDetail!!.description, fontStyle = FontStyle.Normal,
                    fontSize = 16.sp ,
                    modifier = Modifier.padding(start = 10.dp , end =10.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Tags",
                    color = MaterialTheme.colorScheme.surfaceTint ,
                    fontStyle = FontStyle.Italic,
                    fontSize = 28.sp,
                )

                FlowRow {
                    val tags = state.coinDetail!!.tags

                    tags.forEach {
                        Card(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onTertiary),
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = it,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }



                Text(
                    text = "Team",
                    color = MaterialTheme.colorScheme.surfaceTint ,
                    fontStyle = FontStyle.Italic,
                    fontSize = 28.sp,
                )
                FlowRow {
                    val team = state.coinDetail!!.team

                    team.forEach {
                        Card(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onTertiary),
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = it.name,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }

            }

        }
    }

}

   

//@Preview(showSystemUi = true)
//@Composable
//fun p(){
//     coinDetailScreen()
//}
