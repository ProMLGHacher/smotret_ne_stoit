package com.krea.kollege.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class Geocode(
    val response: Response
)