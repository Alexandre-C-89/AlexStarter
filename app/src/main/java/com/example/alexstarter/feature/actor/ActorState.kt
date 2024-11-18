package com.example.alexstarter.feature.actor

import com.example.alexstarter.domain.actor.model.Actor

sealed class ActorState {
    data class Error(val message: String) : ActorState()

    object Loading : ActorState()

    data class Loaded(val actor: Actor) : ActorState()
}