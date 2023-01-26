package com.arad.roomsamples.data

import androidx.room.*

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongs(vararg song: Song)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaylistSong(vararg playlistSongCrossRef: PlaylistSongCrossRef)

    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Transaction
    @Query("SELECT * FROM Song")
    fun getSongsWithPlaylists(): List<SongWithPlaylists>
}