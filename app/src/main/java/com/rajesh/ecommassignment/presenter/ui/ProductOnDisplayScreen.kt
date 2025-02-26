package com.rajesh.ecommassignment.presenter.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rajesh.ecommassignment.domain.model.ProductOnDisplay
import com.rajesh.ecommassignment.presenter.viewmodels.ProductViewModel

@Composable
fun ProductOnDisplayScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit
) {
    when (val productState = viewModel.productState.collectAsState().value) {
        is ProductViewModel.UiState.Loading -> {
            CircularProgressIndicator(Modifier.fillMaxSize())
        }

        is ProductViewModel.UiState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(productState.products) { product ->
                    ProductItem(product, onProductClick = { onProductClick(product.id) })
                }
            }
        }

        is ProductViewModel.UiState.Error -> {
            Text(
                text = "Error ${productState.message}",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ProductItem(
    product: ProductOnDisplay,
    onProductClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onProductClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}