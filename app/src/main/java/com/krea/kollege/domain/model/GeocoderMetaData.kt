package com.krea.kollege.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class GeocoderMetaData(
    val Address: Address,
    val AddressDetails: AddressDetails,
    val kind: String,
    val precision: String,
    val text: String
)