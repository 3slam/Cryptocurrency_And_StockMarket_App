package com.example.cryptocurrencyapp.presentation.coinsList.components
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.domain.entity.Coin
import com.example.cryptocurrencyapp.presentation.navigation.Screen
import com.example.cryptocurrencyapp.ui.theme.md_theme_dark_primary


@Composable
fun coinListItem (
    coinItem: Coin ,
    onItemClick: (Coin) -> Unit
){

    var descriptionVisibility by rememberSaveable { mutableStateOf(false) }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp) ,
        shape = RoundedCornerShape(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(5.dp).clickable {
                    descriptionVisibility = !descriptionVisibility
                },
        ){

            Box (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(text =  coinItem.name , fontSize = 20.sp ,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(5.dp)
                )

            }

            AnimatedVisibility(descriptionVisibility) {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement  =  Arrangement.SpaceEvenly){
                        Card(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(text = "Rank = ${coinItem.rank}",
                            modifier = Modifier.padding(10.dp))
                        }

                        Card(
                            border =  if (coinItem.isActive){
                                BorderStroke(1.dp, Color.Green)
                            }else{
                                BorderStroke(1.dp, Color.Red)
                            }
                         ,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(text = if (coinItem.isActive){
                                "Active"
                            }else{
                                "Not Active"
                            },
                                modifier = Modifier.padding(10.dp))
                        }
                 }
                    Button(
                      onClick = {
                          onItemClick(coinItem)
                      }
                    ) {
                        Text(text = "see more details")
                    }
                }
            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//
//    val x = Coin (
//        "Bit Coin",
//        false,
//        "Bit Coin",
//        1,
//    )
//    coinListItem(x, onItemClick = {
//
//    })
//
//
//}
