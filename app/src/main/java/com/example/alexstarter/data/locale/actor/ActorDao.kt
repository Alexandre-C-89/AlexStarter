package com.example.alexstarter.data.locale.actor

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface ActorDao {

    @Upsert
    suspend fun upsertActor(actor: ActorEntity)

}