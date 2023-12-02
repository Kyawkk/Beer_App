package com.kyawzinlinn.beerapp.data.entities

import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("abv") val abv: String,
    @SerializedName("attenuation_level") val attenuationLevel: String,
    @SerializedName("brewers_tips") val brewersTips: String,
    @SerializedName("contributed_by") val contributedBy: String,
    @SerializedName("description") val description: String,
    @SerializedName("ebc") val ebc: String,
    @SerializedName("first_brewed") val firstBrewed: String,
    @SerializedName("ibu") val ibu: String,
    @SerializedName("id") val beerId: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("ph") val ph: String,
    @SerializedName("srm") val srm: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("target_fg") val targetFg: String,
    @SerializedName("target_og") val targetOg: String,
)

fun List<BeerResponse>.toBeerEntityList(): List<BeerEntity> {
    return map {
        BeerEntity(
            abv = it.abv,
            attenuationLevel = it.attenuationLevel,
            brewersTips = it.brewersTips,
            contributedBy = it.contributedBy,
            description = it.description,
            ebc = it.ebc,
            firstBrewed = it.firstBrewed,
            ibu = it.ibu,
            beerId = it.beerId,
            imageUrl = it.imageUrl,
            name = it.name,
            ph = it.ph,
            srm = it.srm,
            tagline = it.tagline,
            targetFg = it.targetFg,
            targetOg = it.targetOg
        )
    }
}