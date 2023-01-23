package com.krea.kollege.domain.model.geocode
import kotlinx.serialization.Serializable

@Serializable
data class AdministrativeArea(
    val AdministrativeAreaName: String,
    val SubAdministrativeArea: SubAdministrativeArea
)