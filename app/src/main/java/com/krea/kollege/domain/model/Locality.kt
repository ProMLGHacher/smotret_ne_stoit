package com.krea.kollege.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class Locality(
    val LocalityName: String,
    val Thoroughfare: Thoroughfare
)