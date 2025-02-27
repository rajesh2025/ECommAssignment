package com.rajesh.ecommassignment.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
    @SerialName("width") val width: Double? = null,
    @SerialName("height") val height: Double? = null,
    @SerialName("depth") val depth: Double? = null
)