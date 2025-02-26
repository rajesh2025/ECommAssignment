package com.rajesh.ecommassignment.domain.usecase

import com.rajesh.ecommassignment.domain.model.ProductOnDisplay
import com.rajesh.ecommassignment.domain.repository.ProductRepository

class GetProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun  invoke() : Result<List<ProductOnDisplay>> = productRepository.getProductList()
}