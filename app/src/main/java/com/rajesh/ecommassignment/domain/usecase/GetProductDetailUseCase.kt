package com.rajesh.ecommassignment.domain.usecase

import com.rajesh.ecommassignment.domain.model.ProductOnDisplay
import com.rajesh.ecommassignment.domain.repository.ProductRepository

class GetProductDetailUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(id: Int) : Result<ProductOnDisplay> = repository.getProductDetail(id)
}