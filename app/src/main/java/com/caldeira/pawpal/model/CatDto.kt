package com.caldeira.pawpal.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Cat object coming from the /breeds response
 */
@JsonClass(generateAdapter = true)
data class CatDto(
    val weight: WeightDto,
    val id: String,
    val name: String,
    @Json(name = "cfa_url") val cfaUrl: String? = null,
    @Json(name = "vetstreet_url") val vetstreetUrl: String? = null,
    @Json(name = "vcahospitals_url") val vcaHospitalsUrl: String? = null,
    val temperament: String,
    val origin: String,
    @Json(name = "country_codes") val countryCodes: String,
    @Json(name = "country_code") val countryCode: String,
    val description: String,
    @Json(name = "life_span") val lifeSpan: String,
    val indoor: Int,
    val lap: Int? = null,
    @Json(name = "alt_names") val altNames: String? = null,
    val adaptability: Int,
    @Json(name = "affection_level") val affectionLevel: Int,
    @Json(name = "child_friendly") val childFriendly: Int,
    @Json(name = "dog_friendly") val dogFriendly: Int,
    @Json(name = "energy_level") val energyLevel: Int,
    val grooming: Int,
    @Json(name = "health_issues") val healthIssues: Int,
    val intelligence: Int,
    @Json(name = "shedding_level") val sheddingLevel: Int,
    @Json(name = "social_needs") val socialNeeds: Int,
    @Json(name = "stranger_friendly") val strangerFriendly: Int,
    val vocalisation: Int,
    val experimental: Int,
    val hairless: Int,
    val natural: Int,
    val rare: Int,
    val rex: Int,
    @Json(name = "suppressed_tail") val suppressedTail: Int,
    @Json(name = "short_legs") val shortLegs: Int,
    @Json(name = "wikipedia_url") val wikipediaUrl: String? = null,
    val hypoallergenic: Int,
    @Json(name = "reference_image_id") val referenceImageId: String? = null,
)

@JsonClass(generateAdapter = true)
data class WeightDto(
    val imperial: String,
    val metric: String
)

@JsonClass(generateAdapter = true)
data class ImageDto(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    val breeds: List<CatDto>,
)