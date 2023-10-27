package com.jroslar.heroapp.domain.model

import android.os.Parcel
import android.os.Parcelable

data class HeroModel (
    val id: String,
    val name: String,
    val powerstats: PowerstatsModel,
    val biography: BiographyModel,
    val appearance: AppearanceModel,
    val work: WorkModel,
    val image: ImageModel
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        TODO("powerstats"),
        TODO("biography"),
        TODO("appearance"),
        TODO("work"),
        TODO("image")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HeroModel> {
        override fun createFromParcel(parcel: Parcel): HeroModel {
            return HeroModel(parcel)
        }

        override fun newArray(size: Int): Array<HeroModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class PowerstatsModel (
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)

data class BiographyModel (
    val full_name: String,
    val alter_egos: String,
    val aliases: List<String>,
    val place_of_birth: String,
    val first_appearance: String,
    val publisher: String,
    val alignment: String
)

data class AppearanceModel (
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    val eye_color: String,
    val hair_color: String
)

data class WorkModel (
    val occupation: String,
    val base: String
)

data class ImageModel (
    val url: String
)