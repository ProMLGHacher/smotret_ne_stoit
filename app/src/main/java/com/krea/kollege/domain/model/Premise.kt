package com.krea.kollege.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class Premise(
    val PostalCode: PostalCode,
    val PremiseNumber: String
)