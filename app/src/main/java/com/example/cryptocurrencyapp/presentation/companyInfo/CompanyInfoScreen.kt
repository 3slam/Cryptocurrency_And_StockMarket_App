package com.example.cryptocurrencyapp.presentation.companyInfo


import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.font.FontStyle

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import co.yml.charts.common.extensions.isNotNull
import com.example.cryptocurrencyapp.presentation.companyInfo.components.StockChart

@Composable
fun companyInfoScreen(
    navController: NavHostController,
    viewModel: CompanyInfoViewModel = hiltViewModel(),
) {

    val companyInfoState = viewModel.companyInfoState.collectAsState().value
    val companyChartState = viewModel.companyChartState.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        if (companyInfoState.companyInfo.isNotNull()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = CenterHorizontally,

                ) {
                item {

                    Card(
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onTertiary),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = companyInfoState.companyInfo!!.name.plus(" - ${companyInfoState.companyInfo!!.symbol}"),
                            fontStyle = FontStyle.Italic,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )

                    Card(
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onTertiary),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = companyInfoState.companyInfo!!.country!!,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = companyInfoState.companyInfo!!.description!!, fontStyle = FontStyle.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    StockChart(
                   infos = companyChartState.companyIntradayChartInfo,
                   modifier = Modifier
                   .fillMaxWidth()
                   .height(300.dp)

        )


                }

            }

        }
        if(companyInfoState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Center)
            )
        } else if(companyInfoState.error.isNotBlank()) {
            Text(
                text = companyInfoState.error,
                color = MaterialTheme.colorScheme.error ,
                modifier = Modifier.align(Center)
            )
        }

    }

}
