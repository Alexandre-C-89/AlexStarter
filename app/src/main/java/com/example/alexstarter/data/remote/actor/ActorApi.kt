package com.example.alexstarter.data.remote.actor

import com.example.alexstarter.data.remote.actor.di.ActorDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ActorApi {
    @GET("person/{actorId}")
    suspend fun getActorDetails(
        @Path("actorId") actorId: String
    ): ActorDto
}