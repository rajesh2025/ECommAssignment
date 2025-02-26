package com.rajesh.ecommassignment.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ECommProducts(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)