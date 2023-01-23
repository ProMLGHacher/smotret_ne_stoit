package com.krea.kollege.domain.model.geocode
import kotlinx.serialization.Serializable

@Serializable
data class GeocoderResponseMetaData(
    val Point: Point,
    val boundedBy: BoundedByX,
    val found: String,
    val request: String,
    val results: String
)