package com.example.alexstarter.data.repository.actor

import com.example.alexstarter.data.remote.actor.ActorApi
import com.example.alexstarter.data.repository.actor.mapper.toDomain
import com.example.alexstarter.data.repository.actor.mapper.toEntity
import com.example.alexstarter.domain.actor.model.Actor
import com.example.alexstarter.domain.actor.repository.ActorRepository
import com.example.alexstarter.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ActorRepositoryImpl @Inject constructor(
    private val actorApi: ActorApi
): ActorRepository {

    override suspend fun getActorDetails(actorId: String): Flow<Resource<Actor>> = flow {
        emit(Resource.Loading())
        try {
            val actorDto = actorApi.getActorDetails(actorId)
            val actor = actorDto.toEntity().toDomain()
            emit(Resource.Success(actor))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }

}