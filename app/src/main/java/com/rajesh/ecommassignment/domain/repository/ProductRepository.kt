package com.rajesh.ecommassignment.domain.repository

import com.rajesh.ecommassignment.domain.model.ProductOnDisplay

interface ProductRepository {
    suspend fun getProductList() : Result<List<ProductOnDisplay>>
    suspend fun getProductDetail(id: Int) : Result<ProductOnDisplay>
}