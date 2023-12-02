package com.kyawzinlinn.beerapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "beer")
data class BeerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val abv: String?,
    val attenuationLevel: String?,
    val brewersTips: String?,
    val contributedBy: String?,
    val description: String?,
    val ebc: String?,
    val firstBrewed: String?,
    val ibu: String?,
    val beerId: String?,
    val imageUrl: String?,
    val name: String?,
    val ph: String?,
    val srm: String?,
    val tagline: String?,
    val targetFg: String?,
    val targetOg: String?,
): Serializable