package com.krea.kollege.domain.model.geocode
import kotlinx.serialization.Serializable

@Serializable
data class MetaDataProperty(
    val GeocoderMetaData: GeocoderMetaData
)