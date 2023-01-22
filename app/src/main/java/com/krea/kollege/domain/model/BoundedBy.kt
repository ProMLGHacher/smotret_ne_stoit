package com.krea.kollege.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class BoundedBy(
    val Envelope: Envelope
)