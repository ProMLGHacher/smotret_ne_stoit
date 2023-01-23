package com.krea.kollege.domain.model.geocode
import kotlinx.serialization.Serializable

@Serializable
data class GeoObjectCollection(
    val featureMember: List<FeatureMember>,
    val metaDataProperty: MetaDataPropertyX
)