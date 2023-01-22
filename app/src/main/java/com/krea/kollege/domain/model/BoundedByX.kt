package com.krea.kollege.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class BoundedByX(
    val Envelope: Envelope
)