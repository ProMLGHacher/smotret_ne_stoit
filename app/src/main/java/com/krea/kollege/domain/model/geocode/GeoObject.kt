package com.krea.kollege.domain.model.geocode
import kotlinx.serialization.Serializable

@Serializable
data class GeoObject(
    val Point: Point,
    val boundedBy: BoundedBy,
    val description: String,
    val metaDataProperty: MetaDataProperty,
    val name: String
)