package com.arad.roomsamples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.arad.roomsamples.data.*
import com.arad.roomsamples.ui.theme.RoomSamplesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries().build()

            val userDao = db.userDao()
            userDao.insertUsers(
                User(1,"Ahmad",41),
                User(2,"Arad",5))

            val playlistDao = db.playListDao()
            playlistDao.insertPlayLists(Playlist(1,1,"Pop"),
                Playlist(2,1,"Clasic 1"),
                Playlist(3,2,"Clasic 2"),
                Playlist(5,2,"Clasic 3"),
                Playlist(4,2,"Clasic 4"))

            var userWithPlaylists : List<UserWithPlaylists> = userDao.getUsersWithPlaylists()

            val songDao= db.SongDao()
            songDao.insertSongs(
                Song(1,"FlowersMiley","Cyrus"),
            Song(2,"Kill ","BillSZA"),
            Song(3,"Creepin","Savage"),
            Song(4,"Unholy ","Kim Petras"),
            Song(5,"Escapism","Shake"),
            Song(6,"Shakira","Shakira"),
            Song(7,"Calm Down","Selena Gomez"))

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
            var playlistWithSongs: List<PlaylistWithSongs> = songDao.getPlaylistsWithSongs()
            setContent {
                RoomSamplesTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        UserWithPlaylists(userWithPlaylists,playlistWithSongs)
                    }
                }
            }

        }catch (e: Exception){
            println(e.message.toString())
        }
    }
}

@Composable
fun UserWithPlaylists(userWithPlaylists: List<UserWithPlaylists>,playlistWithSongs: List<PlaylistWithSongs>) {
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