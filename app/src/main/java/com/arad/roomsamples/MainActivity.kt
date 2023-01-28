package com.arad.roomsamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.arad.roomsamples.data.db.AppDatabase
import com.arad.roomsamples.model.*
import com.arad.roomsamples.ui.theme.RoomSamplesTheme
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
        GlobalScope.launch(Dispatchers.IO) {


            insertData(db);
            val userWithPlaylists: ArrayList<UserWithPlaylists> = arrayListOf()
            val playlistWithSongs: ArrayList<PlaylistWithSongs> = arrayListOf()
            val userWithPlaylistsAndSongs: ArrayList<UserWithPlaylistsAndSongs> = arrayListOf()

            withContext(Dispatchers.Main) {
                setContent {
                    RoomSamplesTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            UserWithPlaylists(userWithPlaylists,playlistWithSongs,userWithPlaylistsAndSongs)
                        }
                    }
                }
            }

            val userWithPlaylistsFlow=getUsersWithPlaylists(db);
            val playlistWithSongsFlow= getPlaylistsWithSongs(db);
            val userWithPlaylistsAndSongsFlow= getSongsWithPlaylists(db);

            try {
                userWithPlaylistsFlow
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        e.message
                    }
                    .collect {
                        println(it)
                    }

                playlistWithSongsFlow.collect {
                    println(it)
                    //playlistWithSongs.addAll(it)
                }

                userWithPlaylistsAndSongsFlow.collect {
                    println(it)
                    //userWithPlaylistsAndSongs.addAll(it)
                }
            }catch (ex:Exception){
                ex.message
            }







        }


    }
}

suspend fun insertData(db: AppDatabase) {
    val userDao = db.userDao()
    userDao.insertUsers(
        User(1,"Ahmad",41),
        User(2,"Arad",5)
    )

    val playlistDao = db.playListDao()
    playlistDao.insertPlayLists(
        Playlist(1,1,"Pop"),
        Playlist(2,1,"Clasic 1"),
        Playlist(3,2,"Clasic 2"),
        Playlist(5,2,"Clasic 3"),
        Playlist(4,2,"Clasic 4")
    )
    val songDao= db.SongDao()
    songDao.insertSongs(
        Song(1,"FlowersMiley","Cyrus"),
        Song(2,"Kill ","BillSZA"),
        Song(3,"Creepin","Savage"),
        Song(4,"Unholy ","Kim Petras"),
        Song(5,"Escapism","Shake"),
        Song(6,"Shakira","Shakira"),
        Song(7,"Calm Down","Selena Gomez")
    )

    songDao.insertPlaylistSong(
        PlaylistSongCrossRef(1,1),
        PlaylistSongCrossRef(1,2),
        PlaylistSongCrossRef(1,3),
        PlaylistSongCrossRef(1,4),
        PlaylistSongCrossRef(1,5),
        PlaylistSongCrossRef(1,6),
        PlaylistSongCrossRef(1,7),
        PlaylistSongCrossRef(2,1),
        PlaylistSongCrossRef(2,2),
        PlaylistSongCrossRef(2,3),
        PlaylistSongCrossRef(2,4),)
}


fun getUsersWithPlaylists(db: AppDatabase): Flow<List<UserWithPlaylists>> {
    val userDao = db.userDao()
    return userDao.getUsersWithPlaylists()
}

  fun getPlaylistsWithSongs(db: AppDatabase): Flow<List<PlaylistWithSongs>> {
    val songDao= db.SongDao()
    return songDao.getPlaylistsWithSongs()
}

fun getSongsWithPlaylists(db: AppDatabase): Flow<List<UserWithPlaylistsAndSongs>> {
    val userDao = db.userDao()
    return userDao.getUsersWithPlaylistsAndSongs()
}



@Composable
fun UserWithPlaylists(userWithPlaylists: List<UserWithPlaylists>, playlistWithSongs: List<PlaylistWithSongs>, UserWithPlaylistsAndSongs:List<UserWithPlaylistsAndSongs>) {
    LazyColumn(modifier = Modifier.fillMaxWidth(0.5f)) {
        item {
            Card( modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(text = "One-to-Many")
            }

        }
        items(userWithPlaylists) { listItem ->
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(8.dp)

            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp)) {
                    Text(text = "User: ${listItem.user.name}", color = Color.DarkGray)
                    LazyRow(modifier = Modifier.fillMaxHeight()) {
                        itemsIndexed(listItem.playlists) { index,name ->{}
                            Card(modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(8.dp)
                            ) {
                                Text(text = "${index}-${name.playlistName}",color = if(index%2  == 1) Color.Red else Color.Blue )
                            }
                        }
                    }
                }
            }

        }

        item {
            Divider(color = Color.Black)
            Card( modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(text = "Many-to-Many")
            }
        }
        items(playlistWithSongs) { listItem ->
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(8.dp)

            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp)) {
                    Text(text = "Play List: ${listItem.playlist.playlistName}", color = Color.DarkGray)
                    LazyRow(modifier = Modifier.fillMaxHeight()) {
                        itemsIndexed(listItem.songs) { index,name ->{}
                            Card(modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(8.dp)
                            ) {
                                Text(text = "${index}-${name.songName}",color = if(index%2  == 1) Color.Red else Color.Blue )
                            }
                        }
                    }
                }
            }
        }
        item {
            Divider(color = Color.Black)
            Card( modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(text = "Nested")
            }
        }
        items(UserWithPlaylistsAndSongs) { listItem ->
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(8.dp)

            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp)) {
                    Text(text = "Play List: ${listItem.user.name}", color = Color.DarkGray)
                    LazyRow(modifier = Modifier.fillMaxHeight()) {
                        itemsIndexed(listItem.playlists) { index,playList ->{}
                            Card(modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(8.dp)
                            ) {
                                Column() {
                                    Text(text = "Play List: ${playList.playlist.playlistName}", color = Color.DarkGray)
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomSamplesTheme {
        Greeting("Android")
    }
}