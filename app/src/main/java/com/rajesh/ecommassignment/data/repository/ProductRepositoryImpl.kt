package com.rajesh.ecommassignment.data.repository

import com.rajesh.ecommassignment.data.model.ECommProducts
import com.rajesh.ecommassignment.data.model.Product
import com.rajesh.ecommassignment.data.remote.ProductApi
import com.rajesh.ecommassignment.domain.model.ProductOnDisplay
import com.rajesh.ecommassignment.domain.repository.ProductRepository


class ProductRepositoryImpl(
    private val productApi: ProductApi
) : ProductRepository {

    override suspend fun getProductList(): Result<List<ProductOnDisplay>> {
        return try {
            val response: ECommProducts = productApi.getEcommProduct()
            Result.success(response.products.map { it.toProductOnDisplay() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProductDetail(id: Int): Result<ProductOnDisplay> {
        return try {
            val response: Product = productApi.getProductById(id)
            Result.success(response.toProductOnDisplay())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


private fun Product.toProductOnDisplay() =
    ProductOnDisplay(
        id = this.id,
        title = title,
        description = description,
        price = price,
        rating = rating,
        category = category,
        thumbnail = thumbnail,
        images = images
    )