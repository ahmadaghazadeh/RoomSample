package com.arad.roomsamples

import androidx.lifecycle.ViewModel
import com.arad.roomsamples.data.PlayListRepository
import com.arad.roomsamples.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserWithPlaylistsViewModel @Inject constructor(
    private val repository: PlayListRepository): ViewModel() {


    var userWithPlaylists=repository.getUsersWithPlaylists()
    var playlistWithSongs=repository.getPlaylistsWithSongs()
    var userWithPlaylistsAndSongs=repository.getUsersWithPlaylistsAndSongs()

    init{
        GlobalScope.launch {
            insertData()
        }
    }
    private suspend fun insertData() {

        repeat(5) { i ->
            repository.insertUsers(
                User(i.toLong(),"User${i}",i*5),
            )
            delay(2000)
        }

        repeat(5) { i ->
            repository.insertPlayLists(
                Playlist(i.toLong(),i.toLong(),"Clasic 1"),
            )
            delay(4000)
        }

        repository.insertSongs(
            Song(1,"FlowersMiley","Cyrus"),
            Song(2,"Kill ","BillSZA"),
            Song(3,"Creepin","Savage"),
            Song(4,"Unholy ","Kim Petras"),
            Song(5,"Escapism","Shake"),
            Song(6,"Shakira","Shakira"),
            Song(7,"Calm Down","Selena Gomez")
        )
        repeat(5) { i ->
            repository.insertPlaylistSong(
                PlaylistSongCrossRef(i.toLong(),1),
                PlaylistSongCrossRef(i.toLong(),2),
                PlaylistSongCrossRef(i.toLong(),3),
                PlaylistSongCrossRef(i.toLong(),4),
                PlaylistSongCrossRef(i.toLong(),5),
                PlaylistSongCrossRef(i.toLong(),6),
                PlaylistSongCrossRef(i.toLong(),7),)
            delay(5000)
        }

    }
}