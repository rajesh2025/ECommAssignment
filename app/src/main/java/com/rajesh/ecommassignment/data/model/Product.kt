package com.rajesh.ecommassignment.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("availabilityStatus") val availabilityStatus: String? = null,
    @SerialName("brand") val brand: String? = null,
    @SerialName("category") val category: String = "", // Non-null for UI
    @SerialName("description") val description: String = "", // Non-null for UI
    @SerialName("dimensions") val dimensions: Dimensions? = null,
    @SerialName("discountPercentage") val discountPercentage: Double? = null,
    @SerialName("id") val id: Int = 0, // Non-null for navigation
    @SerialName("images") val images: List<String> = listOf(), // Non-null for gallery
    @SerialName("meta") val meta: Meta? = null,
    @SerialName("minimumOrderQuantity") val minimumOrderQuantity: Int? = null,
    @SerialName("price") val price: Double = 0.0, // Non-null for UI
    @SerialName("rating") val rating: Double? = null, // Nullable since optional in UI
    @SerialName("returnPolicy") val returnPolicy: String? = null,
    @SerialName("reviews") val reviews: List<Review> = listOf(),
    @SerialName("shippingInformation") val shippingInformation: String? = null,
    @SerialName("sku") val sku: String? = null,
    @SerialName("stock") val stock: Int? = null,
    @SerialName("tags") val tags: List<String> = listOf(),
    @SerialName("thumbnail") val thumbnail: String = "", // Non-null for UI
    @SerialName("title") val title: String = "", // Non-null for UI
    @SerialName("warrantyInformation") val warrantyInformation: String? = null,
    @SerialName("weight") val weight: Int? = null
)