package com.rajesh.ecommassignment.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rajesh.ecommassignment.domain.model.ProductOnDisplay
import com.rajesh.ecommassignment.domain.usecase.GetProductDetailUseCase
import com.rajesh.ecommassignment.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val products: List<ProductOnDisplay>) : UiState()
        data class Error(val message: String) : UiState()
    }

    sealed class DetailState {
        object Loading : DetailState()
        data class Success(val product: ProductOnDisplay) : DetailState()
        data class Error(val message: String) : DetailState()
    }

    private val _productState = MutableStateFlow<UiState>(UiState.Loading)
    val productState: StateFlow<UiState> = _productState

    private val _detailState = MutableStateFlow<DetailState>(DetailState.Loading)
    val detailState: StateFlow<DetailState> = _detailState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _productState.value = UiState.Loading
            getProductsUseCase().fold(
                onSuccess = { products -> _productState.value = UiState.Success(products) },
                onFailure = { error ->
                    _productState.value = UiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }

     fun fetchProductDetails(id: Int) {
        viewModelScope.launch {
            _detailState.value = DetailState.Loading
            getProductDetailUseCase(id).fold(
                onSuccess = { product -> _detailState.value = DetailState.Success(product) },
                onFailure = { error ->
                    _detailState.value = DetailState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }

}