package com.caldeira.pawpal.model

fun CatDetails.toEntity(): BreedDetailsEntity = BreedDetailsEntity(
    id = id,
    name = name,
    lifeExpectancy = lifeExpectancy,
    isFavorite = isFavorite,
    origin = origin,
    temperament = temperament,
    description = description,
    imageUrl = imageUrl,
)

fun BreedDetailsEntity.toCatDetails(): CatDetails = CatDetails(
    id = id,
    name = name,
    lifeExpectancy = lifeExpectancy,
    isFavorite = isFavorite,
    origin = origin,
    temperament = temperament,
    description = description,
    imageUrl = imageUrl,
)

fun List<BreedDetailsEntity>.toCatDetailsList() = map { it.toCatDetails() }