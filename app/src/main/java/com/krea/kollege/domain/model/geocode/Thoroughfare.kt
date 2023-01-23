package com.krea.kollege.domain.model.geocode
import kotlinx.serialization.Serializable

@Serializable
data class Thoroughfare(
    val Premise: Premise,
    val ThoroughfareName: String
)