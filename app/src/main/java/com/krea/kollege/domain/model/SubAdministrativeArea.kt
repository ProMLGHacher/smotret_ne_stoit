package com.krea.kollege.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class SubAdministrativeArea(
    val Locality: Locality,
    val SubAdministrativeAreaName: String
)