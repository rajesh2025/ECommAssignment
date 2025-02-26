package com.rajesh.ecommassignment.data.remote

import com.rajesh.ecommassignment.data.model.ECommProducts
import com.rajesh.ecommassignment.data.model.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductApi(private val client: HttpClient) {

    suspend fun getEcommProduct(): ECommProducts =
        client.get("https://dummyjson.com/products").body()

    suspend fun getProductById(id: Int): Product {
        return client.get("https://dummyjson.com/products/$id").body()
    }
}