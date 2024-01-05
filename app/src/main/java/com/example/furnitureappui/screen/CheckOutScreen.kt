package com.example.furnitureappui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.furnitureappui.CheckOut
import com.example.furnitureappui.Home
import com.example.furnitureappui.R
import com.example.furnitureappui.ui.theme.Background_1
import com.example.furnitureappui.ui.theme.DarkOrange
import com.example.furnitureappui.ui.theme.GrayColor
import com.example.furnitureappui.ui.theme.LightGray_1
import com.example.furnitureappui.ui.theme.LightOrange
import com.example.furnitureappui.ui.theme.LineColor
import com.example.furnitureappui.ui.theme.TextColor_1
import com.nameisjayant.projects.furniture.data.ShoppingBag
import com.nameisjayant.projects.furniture.data.popularProductList
import com.nameisjayant.projects.furniture.data.shoppingList


@Composable
fun CheckOutScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(20.dp)) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)

            }
            Text(
                text = stringResource(id = R.string.my_shopping_bag), style = TextStyle(
                    color = Color.Black,
                    fontSize = 29.sp, fontWeight = FontWeight.W800
                ), modifier = Modifier.padding(vertical = 15.dp)
            )

            LazyColumn(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)) {

                items(shoppingList, key = { it.id }) {
                    ShoppingEachRow(data = it)
                }

                item{
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),color = LineColor)
                    Spacer(modifier = Modifier.height(10.dp))

                    RecommendProduct()

                    CheckOutButton {

                        navController.navigate(Home)
                    }
                }
            }


        }



    }
}


@Composable
fun ShoppingEachRow(
    data: ShoppingBag,
    modifier: Modifier = Modifier,

) {

    var count by remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.padding(vertical = 14.dp)){

        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = data.icon), contentDescription = data.title,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(80.dp)
                    .align(CenterVertically)
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = data.title, style = TextStyle(
                            color = TextColor_1,
                            fontSize = 17.sp, fontWeight = FontWeight.W400
                        ), modifier = Modifier.weight(1f)
                    )


                    Box(
                        modifier = Modifier
                            .background(Color.White, CircleShape)
                            .border(
                                1.dp,
                                DarkOrange, CircleShape
                            )
                            .size(30.dp), contentAlignment = Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Text(
                    text = "Qty : ${data.qty}", style = TextStyle(
                        color = LightGray_1,
                        fontSize = 12.sp, fontWeight = FontWeight.W400
                    ),
                )


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp), horizontalArrangement = Arrangement.SpaceBetween) {


                    Row(modifier = Modifier.weight(1f),)
                    {
                        ProductCountButton {
                            if (count > 0) {
                                count--
                            }
                        }
                        Text(
                            text = "$count", modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .align(CenterVertically)
                        )

                        ProductCountButton {
                            count++

                        }
                    }

                    Text(
                        text = data.price, style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W400, color = DarkOrange
                        ), modifier = Modifier
                            .weight(1f)
                            .align(CenterVertically)
                    )
                }


            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = LineColor, thickness = 1.dp,modifier = Modifier.fillMaxWidth())
    }

}

@Composable
fun CheckOutButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(modifier = modifier.fillMaxWidth(), color = LineColor)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {

            Column {
                Text(
                    text = "Total", style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    )
                )

                Text(
                    text = "$ 93432.234", style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = onClick, modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .fillMaxWidth()
                    .weight(0.7f),
                shape = RoundedCornerShape(9.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TextColor_1,
                    contentColor = Color.White
                ), elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.proceed_to_checkout), style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White
                    )
                )
            }
        }

    }


}
