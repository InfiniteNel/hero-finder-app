package com.jroslar.heroapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.jroslar.heroapp.domain.model.AppearanceModel
import com.jroslar.heroapp.domain.model.BiographyModel
import com.jroslar.heroapp.domain.model.HeroModel
import com.jroslar.heroapp.domain.model.ImageModel
import com.jroslar.heroapp.domain.model.PowerstatsModel
import com.jroslar.heroapp.domain.model.WorkModel


data class ListHeroResponse (
    @SerializedName("response")
    val response: String,
    @SerializedName("result-for")
    val result_for: String,
    @SerializedName("results")
    val results: List<HeroResponse>
)
data class HeroResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("powerstats")
    val powerstats: Powerstats,
    @SerializedName("biography")
    val biography: Biography,
    @SerializedName("appearance")
    val appearance: Appearance,
    @SerializedName("work")
    val work: Work,
    @SerializedName("image")
    val image: Image
)

data class Powerstats (
    @SerializedName("intelligence")
    val intelligence: String,
    @SerializedName("strength")
    val strength: String,
    @SerializedName("speed")
    val speed: String,
    @SerializedName("durability")
    val durability: String,
    @SerializedName("power")
    val power: String,
    @SerializedName("combat")
    val combat: String
)

data class Biography (
    @SerializedName("full-name")
    val full_name: String,
    @SerializedName("alter-egos")
    val alter_egos: String,
    @SerializedName("aliases")
    val aliases: List<String>,
    @SerializedName("place-of-birth")
    val place_of_birth: String,
    @SerializedName("first-appearance")
    val first_appearance: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("alignment")
    val alignment: String
)

data class Appearance (
    @SerializedName("gender")
    val gender: String,
    @SerializedName("race")
    val race: String,
    @SerializedName("height")
    val height: List<String>,
    @SerializedName("weight")
    val weight: List<String>,
    @SerializedName("eye-color")
    val eye_color: String,
    @SerializedName("hair-color")
    val hair_color: String
)

data class Work (
    @SerializedName("occupation")
    val occupation: String,
    @SerializedName("base")
    val base: String
)

data class Image (
    @SerializedName("url")
    val url: String
)

fun HeroResponse.toModelHero(): HeroModel = HeroModel(id, name, powerstats.toModelPowerstats(), biography.toModelBiography(), appearance.toModelAppearance(), work.toModelWork(), image.toModelImage())

fun Powerstats.toModelPowerstats(): PowerstatsModel = PowerstatsModel(intelligence, strength, speed, durability, power, combat)

fun Biography.toModelBiography(): BiographyModel = BiographyModel(full_name, alter_egos, aliases, place_of_birth, first_appearance, publisher, alignment)

fun Appearance.toModelAppearance(): AppearanceModel = AppearanceModel(gender, race, height, weight, eye_color, hair_color)

fun Work.toModelWork(): WorkModel = WorkModel(occupation, base)

fun Image.toModelImage(): ImageModel = ImageModel(url)