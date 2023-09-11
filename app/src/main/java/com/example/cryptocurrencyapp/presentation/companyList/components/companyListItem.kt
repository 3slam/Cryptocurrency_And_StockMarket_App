package com.example.cryptocurrencyapp.presentation.companyList.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.domain.entity.Company
import com.example.cryptocurrencyapp.presentation.navigation.Screen


@Composable
fun companyItem(
    navController : NavHostController ,
    company: Company,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(5.dp),
        ) {


            Text(
                text = company.name, fontSize = 17.sp,
                modifier = Modifier

                    .padding(5.dp)
            )

            Spacer(modifier = Modifier.height( 10.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement  =  Arrangement.SpaceEvenly){
                Card(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),

                    ) {
                    Text(
                        text = company.symbol,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),

                    ) {
                    Text(
                        text = company.exchange,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "see more details",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(5.dp).clickable {
                            navController.navigate(Screen.CompanyInfo.route + "/${company.symbol}")
                        },
                    textDecoration = TextDecoration.Underline ,
                    fontSize = 15.sp
                )

            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {

    val x = Company (
        "Bit Coin Bit Coin Bit Coin Bit Coin Bit Coin Bit Coin","Bit Coin", "Bit Coin",
        )

    companyItem(navController = rememberNavController() ,x)


}
