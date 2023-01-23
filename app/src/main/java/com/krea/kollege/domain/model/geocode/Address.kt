package com.krea.kollege.domain.model.geocode

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val Components: List<Component>,
    val country_code: String,
    val formatted: String,
    val postal_code: String
)