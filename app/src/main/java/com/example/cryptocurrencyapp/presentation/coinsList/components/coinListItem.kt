package com.example.cryptocurrencyapp.presentation.coinsList.components
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.dataSource.remote.coinsResponse.CoinDto
import com.example.cryptocurrencyapp.domain.entity.Coin


@Composable
fun coinListItem (
    coinsDtoItem: Coin ,
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
        Column (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp) ,
        ){

            Box (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(text =  coinsDtoItem.name , fontSize = 20.sp ,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(5.dp)
                )

                Icon(
                    painter = if(descriptionVisibility )
                        painterResource(id = R.drawable.up_arrow)
                    else painterResource(id = R.drawable.down_arrow)
                    ,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(30.dp)
                        .padding(5.dp)
                        .clickable {
                            descriptionVisibility  = !descriptionVisibility
                        } )
            }

            AnimatedVisibility(descriptionVisibility) {
                Column  {
                    Text(text =   coinsDtoItem.id ,
                        fontSize = 18.sp ,
                        modifier = Modifier
                            .padding(5.dp)
                    )

                    Text(text = "See more details" ,
                        fontSize = 20.sp ,
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally).clickable {
                                onItemClick(coinsDtoItem)
                            }
                    )
                }
            }
        }
    }
}
