package com.rajesh.ecommassignment.presenter.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rajesh.ecommassignment.R
import com.rajesh.ecommassignment.presenter.ui.ProductDetailScreen
import com.rajesh.ecommassignment.presenter.ui.ProductOnDisplayScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (currentRoute) {
                            "product_list" -> "E-Commerce App"
                            "product_detail/{productId}" -> "Product Details"
                            else -> "E-Commerce App"
                        }
                    )
                },
                navigationIcon = {
                    when (currentRoute) {
                        "product_list" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your logo
                                contentDescription = "App Logo",
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        "product_detail/{productId}" -> {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        NavHost(navController,
            "product_list",
            modifier = modifier.padding(paddingValues)) {
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
}
