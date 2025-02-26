package com.rajesh.ecommassignment.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rajesh.ecommassignment.presenter.ui.ProductDetailScreen
import com.rajesh.ecommassignment.presenter.ui.ProductOnDisplayScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, "product_list") {

        composable("product_list") {
            ProductOnDisplayScreen(onProductClick = { productId ->
                navController.navigate("product_detail/$productId")
            })
        }

        composable("product_detail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 1
            ProductDetailScreen(productId = productId)
        }
    }
}