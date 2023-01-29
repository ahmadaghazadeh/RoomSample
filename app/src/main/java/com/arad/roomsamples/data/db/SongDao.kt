package com.arad.roomsamples.data.db

import androidx.room.*
import com.arad.roomsamples.model.PlaylistSongCrossRef
import com.arad.roomsamples.model.PlaylistWithSongs
import com.arad.roomsamples.model.Song
import com.arad.roomsamples.model.SongWithPlaylists
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertSongs(vararg song: Song)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend   fun insertPlaylistSong(vararg playlistSongCrossRef: PlaylistSongCrossRef)

    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getPlaylistsWithSongs(): Flow<List<PlaylistWithSongs>>

    @Transaction
    @Query("SELECT * FROM Song")
    fun getSongsWithPlaylists(): Flow<List<SongWithPlaylists>>
}