package com.krea.kollege.domain.model.geocode
import kotlinx.serialization.Serializable

@Serializable
data class Component(
    val kind: String,
    val name: String
)