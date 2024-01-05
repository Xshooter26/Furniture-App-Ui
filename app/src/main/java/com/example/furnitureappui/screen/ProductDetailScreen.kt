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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
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
import com.example.furnitureappui.R
import com.example.furnitureappui.ui.theme.Background_1
import com.example.furnitureappui.ui.theme.DarkOrange
import com.example.furnitureappui.ui.theme.GrayColor
import com.example.furnitureappui.ui.theme.LightGray_1
import com.example.furnitureappui.ui.theme.LightOrange
import com.example.furnitureappui.ui.theme.LineColor
import com.example.furnitureappui.ui.theme.TextColor_1
import com.nameisjayant.projects.furniture.data.popularProductList



@Composable
fun ProductDetailScreen(
    navController: NavController
) {

    val chips = listOf("Description", " Material", "Review")
    var selected by remember {
        mutableStateOf(0)
    }

    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painterResource(id = R.drawable.product_four), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillWidth

        )
        HeaderSection {
             navController.navigateUp()
        }

        Box(
            modifier = Modifier
                .padding(top = 250.dp)
                .fillMaxSize()
                .background(
                    Color.White,
                    RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
        ) {
            LazyColumn(modifier = Modifier.padding(bottom = 70.dp)) {
                item {

                    ProductHeaderSection()
                    RatingRow()
                    Spacer(modifier = Modifier.height(15.dp))
                    Row {
                        chips.forEachIndexed { index, s ->
                            CustomChips(title = s,
                                selected = index == selected,
                                index = index,
                                onValueChange = { selected = it })

                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = stringResource(id = R.string.description),
                        style = TextStyle(
                            color = LightGray_1,
                            fontWeight = FontWeight.W600,
                            fontSize = 14.sp
                        ), modifier = Modifier.padding(20.dp)
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(modifier = Modifier.fillMaxWidth(), color = GrayColor, thickness = 5.dp)
                    RecommendProduct()

                    BottomBarButton {
                        navController.navigate(CheckOut)
                    }
                }




            }


        }



    }
}

@Composable
fun BottomBarButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(modifier = modifier.fillMaxWidth(), color = LineColor)

       Row(modifier = Modifier
           .fillMaxWidth()
           .padding(20.dp)) {
            TextButton(
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(40.dp)
                   ,
                border = BorderStroke(1.dp, LightGray_1),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White,
                )
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    modifier = Modifier.size(16.dp),
                    tint = LightGray_1
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
                    text = stringResource(id = R.string.add_to_ba), style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White
                    )
                )
            }
        }

    }


}




@Composable
fun ProductHeaderSection() {

    var count by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.product_name), style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W400, color = TextColor_1
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = stringResource(id = R.string._599), style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400, color = TextColor_1
                ), modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
            )

            ProductCountButton {
                if (count > 0) {
                    count--
                }
            }
            Text(text = "$count", modifier = Modifier
                .padding(horizontal = 10.dp)
                .align(CenterVertically))

            ProductCountButton {
                count++

            }


        }
    }
}


@Composable
fun RatingRow() {

    val personIcons = listOf(
        R.drawable.person_1,
        R.drawable.person_2,
        R.drawable.person_3,
        R.drawable.person_4
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .background(Background_1, RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Column(Modifier.weight(1f)) {
                Row {
                    repeat(5) {
                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "", tint = Color.Unspecified
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "5.0", style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400, color = TextColor_1
                        ), modifier = Modifier.align(CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Text(
                        text = stringResource(id = R.string._98_reviews), style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400, color = LightGray_1
                        )
                    )

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp),
                        tint = LightGray_1
                    )
                }


            }


            Row {
                personIcons.forEachIndexed { index, i ->
                    Icon(
                        painter = painterResource(id = i),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp)
                            .offset(
                                x = if (index == 1) -(20.dp) else if (index == 2) -(20.dp) else if (index == 3) -(30.dp) else 0.dp,
                                y = 0.dp
                            )

                    )
                }
            }
        }
    }

}

@Composable
fun ProductCountButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp), modifier = Modifier.size(35.dp),
        border = BorderStroke(2.dp, DarkOrange),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Icon(Icons.Default.Add, contentDescription = "", tint = DarkOrange)

    }
}

@Composable
fun HeaderSection(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
        }

        IconButton(onClick = onBack) {
            Icon(
                painter = painterResource(id = R.drawable.share),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}


@Composable
fun CustomChips(
    title: String,
    selected: Boolean,
    index: Int,
    modifier: Modifier = Modifier,
    onValueChange: (Int) -> Unit
) {


    TextButton(
        onClick = { onValueChange(index) },
        shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) DarkOrange else Color.Transparent,
            contentColor = if (selected) Color.White else LightGray_1
        ), elevation = ButtonDefaults.buttonElevation(0.dp),
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = title, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,

                )
        )

    }
}


@Composable
fun RecommendProduct() {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.recommend_products),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W600, color = TextColor_1)
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
            items(popularProductList, key = { it.id }) {
                PopularEachRow(data = it, modifier = Modifier.padding(20.dp)) {

                }
            }
        }
    }
}
