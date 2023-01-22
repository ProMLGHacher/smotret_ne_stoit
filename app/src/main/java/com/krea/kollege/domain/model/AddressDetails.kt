package com.krea.kollege.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class AddressDetails(
    val Country: Country
)