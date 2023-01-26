package com.arad.roomsamples.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithPlaylistsAndSongs(
    @Embedded val user: User,
    @Relation(
        entity = Playlist::class,
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )
    val playlists: List<PlaylistWithSongs>
)