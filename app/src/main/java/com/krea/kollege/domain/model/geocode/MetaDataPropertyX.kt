package com.krea.kollege.domain.model.geocode
import kotlinx.serialization.Serializable

@Serializable
data class MetaDataPropertyX(
    val GeocoderResponseMetaData: GeocoderResponseMetaData
)