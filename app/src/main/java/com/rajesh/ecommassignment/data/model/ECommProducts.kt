package com.rajesh.ecommassignment.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ECommProducts(
    @SerialName("limit")
    val limit: Int = 0,
    @SerialName("products")
    val products: List<Product> = listOf(),
    @SerialName("skip")
    val skip: Int = 0,
    @SerialName("total")
    val total: Int = 0
)
