package com.krea.kollege.domain.model.geocode
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val AddressLine: String,
    val AdministrativeArea: AdministrativeArea,
    val CountryName: String,
    val CountryNameCode: String
)