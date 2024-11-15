package com.example.alexstarter.data.repository.actor.mapper

import com.example.alexstarter.data.locale.actor.ActorEntity
import com.example.alexstarter.data.remote.actor.di.ActorDto
import com.example.alexstarter.data.remote.di.RemoteModule.Companion.IMAGE_BASE_URL
import com.example.alexstarter.domain.actor.model.Actor

fun ActorDto.toEntity(): ActorEntity {
    return ActorEntity(
        id = id,
        adult = adult,
        biography = biography,
        birthday = birthday,
        deathday = deathday,
        gender = gender,
        homepage = homepage,
        knownForDepartment = knownForDepartment,
        name = name,
        placeOfBirth = placeOfBirth,
        popularity = popularity,
        profilePath = profilePath
    )
}

fun ActorEntity.toDomain(): Actor {
    return Actor(
        id = id,
        adult = adult,
        biography = biography,
        birthday = birthday,
        deathday = deathday,
        gender = gender,
        homepage = homepage,
        knownForDepartment = knownForDepartment,
        name = name,
        placeOfBirth = placeOfBirth,
        popularity = popularity,
        profilePath = IMAGE_BASE_URL + profilePath
    )
}