package com.jroslar.heroapp.domain.model

data class HeroModel (
    val id: String,
    val name: String,
    val powerstats: PowerstatsModel,
    val biography: BiographyModel,
    val appearance: AppearanceModel,
    val work: WorkModel,
    val image: ImageModel
)

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