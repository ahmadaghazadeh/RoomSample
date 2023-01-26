package com.arad.roomsamples.data

import androidx.room.*

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertSongs(vararg song: Song)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend   fun insertPlaylistSong(vararg playlistSongCrossRef: PlaylistSongCrossRef)

    @Transaction
    @Query("SELECT * FROM Playlist")
    suspend   fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Transaction
    @Query("SELECT * FROM Song")
    suspend  fun getSongsWithPlaylists(): List<SongWithPlaylists>
}