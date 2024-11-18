package com.example.alexstarter.feature.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alexstarter.domain.actor.model.Actor
import com.example.alexstarter.domain.actor.repository.ActorRepository
import com.example.alexstarter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(
    private val actorRepository: ActorRepository
) : ViewModel() {

    private val _actorState = MutableStateFlow<ActorState>(ActorState.Loading)
    val actorState: StateFlow<ActorState> = _actorState

    fun fetchActorDetails(actorId: String) {
        viewModelScope.launch {
            actorRepository.getActorDetails(actorId = actorId)
                .flowOn(Dispatchers.IO)
                .collect { actorDetailsResource ->
                    _actorState.value = when (actorDetailsResource) {
                        is Resource.Loading -> ActorState.Loading
                        is Resource.Success -> {
                            actorDetailsResource.data?.let { actor ->
                                ActorState.Loaded(actor)
                            } ?: ActorState.Error("Actor data is null")
                        }
                        is Resource.Error -> actorDetailsResource.message?.let { ActorState.Error(it) }!!
                    }
                }
        }
    }
}