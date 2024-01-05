package com.example.furnitureappui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.furnitureappui.screen.CheckOutScreen
import com.example.furnitureappui.screen.HomeScreen
import com.example.furnitureappui.screen.ProductDetailScreen


const val Home = "home_screen"
const val ProductDetail = "product_detail_screen"
const val CheckOut = "check_out_screen"

@Composable
fun FurnitureNavigation(

){
     val navController = rememberNavController()

    NavHost(navController, startDestination = Home){
        composable(Home){
            HomeScreen(navController)
        }
        composable(ProductDetail){
            ProductDetailScreen(navController)
        }
        composable(CheckOut){
            CheckOutScreen(navController)
        }
    }


}

