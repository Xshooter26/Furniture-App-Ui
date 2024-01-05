package com.example.furnitureappui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.furnitureappui.ProductDetail
import com.example.furnitureappui.R
import com.example.furnitureappui.ui.theme.DarkOrange
import com.example.furnitureappui.ui.theme.LightGray_1
import com.nameisjayant.projects.furniture.data.Category
import com.nameisjayant.projects.furniture.data.PopularProducts
import com.nameisjayant.projects.furniture.data.Rooms
import com.nameisjayant.projects.furniture.data.categoryList
import com.nameisjayant.projects.furniture.data.popularProductList
import com.nameisjayant.projects.furniture.data.roomList


@Composable
fun HomeScreen(
    navController: NavController
) {


    var text by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White)
    ) {
        item {

            Header()
            Spacer(modifier = Modifier.height(15.dp))
            CustomTextField(text = text, onValueChange = { text = it })
            Spacer(modifier = Modifier.height(15.dp))
            CategoryRow()
            Spacer(modifier = Modifier.height(15.dp))
            PopularRow {
                navController.navigate(ProductDetail)
            }
            BannerRow()
            Rooms()
        }

    }
}

@Composable
fun CategoryRow() {
    Column {

        CommonTitle(title = stringResource(id = R.string.categories))
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
            items(categoryList, key = { it.id }) {
                CategoryEachRow(category = it)

            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularRow(onClick: () -> Unit) {
    Column {
        CommonTitle(title = "Popular")
        Spacer(modifier = Modifier.height(20.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            popularProductList.forEach {
                PopularEachRow(data = it){
                    onClick()
                }
            }
        }
    }
}

@Composable
fun PopularEachRow(data: PopularProducts, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {

    Column(modifier = modifier
        .clickable { onClick() }
        .padding(vertical = 10.dp)) {
        Box {
            Image(
                painter = painterResource(id = data.image),
                contentDescription = "",
                modifier = Modifier
                    .width(141.dp)
                    .height(149.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.wishlist), contentDescription = null,
                modifier = Modifier
                    .padding(15.dp)
                    .size(32.dp)
                    .align(TopEnd),
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            modifier = Modifier
                .align(CenterHorizontally)
                .background(Color.White),
            colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)) {
                Text(
                    text = data.title,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = LightGray_1
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = data.price,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    )

                )
            }

        }
    }
}

@Composable
fun CategoryEachRow(category: Category) {
    Box(
        modifier = Modifier
            .padding(end = 15.dp)
            .background(category.color, RoundedCornerShape(15.dp))
            .width(140.dp)
            .height(80.dp)
    ) {
        Text(
            text = category.title,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W600, color = Color.Black),
            modifier = Modifier
                .padding(start = 5.dp)
                .align(CenterStart)
        )

        Image(
            painter = painterResource(id = category.image), contentDescription = "category",
            modifier = Modifier
                .padding(end = 5.dp)
                .size(60.dp)
                .align(BottomEnd)
        )
    }
}


@Composable
fun CommonTitle(
    title: String,
    onClick: () -> Unit = {}
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = title, style = TextStyle(
                fontSize = 20.sp, fontWeight = FontWeight.W600,
                color = Color.Black
            ), modifier = Modifier.align(CenterVertically)
        )
        TextButton(onClick = onClick) {
            Text(
                text = "See All",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = DarkOrange
                )
            )
            Spacer(modifier = Modifier.width(3.dp))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = DarkOrange,
                modifier = Modifier.size(20.dp)
            )

        }
    }

}

@Composable
fun Header(onClick: () -> Unit = {}) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {


        Text(
            text = stringResource(id = R.string.heading_text),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            )
        )

        IconButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .size(52.dp)
                .align(CenterVertically)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "notification",
                tint = MaterialTheme.colorScheme.inversePrimary
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text, onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = DarkOrange,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.placeholder),
                style = TextStyle(
                    color = LightGray_1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.search), contentDescription = "")
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, LightGray_1),
        shape = RoundedCornerShape(8.dp), singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    )
}


@Composable
fun BannerRow() {
    Image(
        painter = painterResource(id = R.drawable.banner), contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(113.dp), contentScale = ContentScale.FillWidth
    )

}


@Composable
fun  Rooms(){
    Column {
        Text(
            text = stringResource(id = R.string.rooms),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            ))
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.room_des),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = LightGray_1
            ))
        
        Spacer(modifier = Modifier.height(15.dp))
            
        LazyRow{
            items(roomList,key = {it.id}){
               RoomSection(rooms = it)
            }
        }

    }
}


@Composable
fun RoomSection(rooms: Rooms){
    Box(modifier = Modifier.padding(15.dp)){


        Image(painter = painterResource(id = rooms.image), contentDescription ="",modifier = Modifier
            .width(127.dp)
            .height(195.dp),)


        Text(text = rooms.title, style = TextStyle(fontSize = 14.sp,
            fontWeight = FontWeight.W100,
            color = Color.Black),modifier = Modifier.align(TopEnd).padding(5.dp))


    }

}




