package com.rajesh.ecommassignment.presenter.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rajesh.ecommassignment.presenter.viewmodels.ProductViewModel

@Composable
fun ProductDetailScreen(
    productId: Int,
    viewModel: ProductViewModel = hiltViewModel(),
) {

    LaunchedEffect(productId) {
        viewModel.fetchProductDetails(productId)
    }
    when(val productDetailState = viewModel.detailState.collectAsState().value){
        is ProductViewModel.DetailState.Error -> {
            Text(text = "Error ${productDetailState.message}", modifier = Modifier.fillMaxSize().padding(16.dp))
        }
        ProductViewModel.DetailState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }
        is ProductViewModel.DetailState.Success -> {
            // Image Gallery
            LazyRow {
                items(productDetailState.product.images) { image ->
                    AsyncImage(
                        model = image,
                        contentDescription = productDetailState.product.title,
                        modifier = Modifier.size(200.dp).padding(end = 8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = productDetailState.product.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Category: ${productDetailState.product.category}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price: $${productDetailState.product.price}", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            productDetailState.product.rating?.let {
                Text(text = "Rating: $it", style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = productDetailState.product.description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}