package com.rajesh.ecommassignment.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName("rating") val rating: Int? = null,
    @SerialName("comment") val comment: String? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("reviewerName") val reviewerName: String? = null,
    @SerialName("reviewerEmail") val reviewerEmail: String? = null
)