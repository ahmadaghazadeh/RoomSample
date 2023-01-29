package com.arad.roomsamples.data
import javax.inject.Inject
import com.arad.roomsamples.data.db.PlayListDao
import com.arad.roomsamples.data.db.PlayListDatabase
import com.arad.roomsamples.data.db.SongDao
import com.arad.roomsamples.data.db.UserDao
import com.arad.roomsamples.model.*

class PlayListRepository  @Inject constructor(
    private val userDao: UserDao,
    private val songDao: SongDao,
    private val playListDao: PlayListDao,
) {

    suspend fun insertUsers(vararg users: User) = userDao.insertUsers(*users)

    fun getUsersWithPlaylistsAndSongs() = userDao.getUsersWithPlaylistsAndSongs()

    fun getUsersWithPlaylists() = userDao.getUsersWithPlaylists()

    fun getUsers() = userDao.getUsers()


    suspend fun insertSongs(vararg song: Song) = songDao.insertSongs(*song)

    suspend fun insertPlaylistSong(vararg playlistSongCrossRef: PlaylistSongCrossRef) = songDao.insertPlaylistSong(* playlistSongCrossRef)

    fun getPlaylistsWithSongs() = songDao.getPlaylistsWithSongs()

    fun getSongsWithPlaylists() = songDao.getSongsWithPlaylists()

    suspend fun insertPlayLists(vararg playlist: Playlist) = playListDao.insertPlayLists(* playlist)

}