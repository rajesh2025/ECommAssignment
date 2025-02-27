package com.rajesh.ecommassignment.presenter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.rajesh.ecommassignment.presenter.viewmodels.ProductViewModel

@Composable
fun ProductDetailScreen(
    productId: Int,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val state = viewModel.detailState.collectAsState().value

    LaunchedEffect(productId) {
        viewModel.fetchProductDetails(productId)
    }

    when (state) {
        is ProductViewModel.DetailState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(100.dp))
            }
        }

        is ProductViewModel.DetailState.Success -> {
            val product = state.product
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ImageGallery(images = product.images)

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = product.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Category: ${product.category}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Price: $${product.price}", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                product.rating?.let {
                    Text(text = "Rating: $it", style = MaterialTheme.typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
            }
        }

        is ProductViewModel.DetailState.Error -> {
            Text(
                text = "Error: ${state.message}",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ImageGallery(images: List<String>) {
    val lazyListState = rememberLazyListState()
    val visibleItemIndex = remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) 0 else visibleItems.first().index
        }
    }

    LazyRow(
        state = lazyListState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(images) { image ->
            Box(
                modifier = Modifier.fillParentMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    model = image,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.LightGray), // Placeholder background
                    loading = {
                        Box(
                            modifier = Modifier.matchParentSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(32.dp))
                        }
                    },
                    error = {
                        Box(
                            modifier = Modifier.matchParentSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Failed to load", color = Color.Red)
                        }
                    }
                )
            }
        }
    }

    // Pagination Dots
    if (images.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            images.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .padding(2.dp)
                        .background(
                            color = if (index == visibleItemIndex.value) MaterialTheme.colorScheme.primary else Color.Gray,
                            shape = MaterialTheme.shapes.small
                        )
                )
            }
        }
    }
}