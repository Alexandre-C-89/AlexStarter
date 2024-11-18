package com.example.alexstarter.domain.actor.repository

import com.example.alexstarter.domain.actor.model.Actor
import com.example.alexstarter.util.Resource
import kotlinx.coroutines.flow.Flow

interface ActorRepository {
    suspend fun getActorDetails(actorId: String): Flow<Resource<Actor>>
}