package com.krea.kollege.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class Envelope(
    val lowerCorner: String,
    val upperCorner: String
)