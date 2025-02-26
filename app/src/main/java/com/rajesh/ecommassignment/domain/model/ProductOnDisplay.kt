package com.rajesh.ecommassignment.domain.model

data class ProductOnDisplay(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val rating: Double?,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)
