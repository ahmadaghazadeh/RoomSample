package com.arad.roomsamples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.arad.roomsamples.data.AppDatabase
import com.arad.roomsamples.data.Playlist
import com.arad.roomsamples.data.User
import com.arad.roomsamples.data.UserWithPlaylists
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
            var userWithPlaylists : List<UserWithPlaylists> = listOf()
            CoroutineScope(Dispatchers.IO).launch {
                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()

                val userDao = db.userDao()
                userDao.insertUsers(
                    User(1,"Ahmad",41),
                    User(2,"Arad",5))

                val playlist = db.playListDao()
                playlist.insertPlayLists(Playlist(1,1,"Pop"),
                    Playlist(2,2,"Clasic"))

                userWithPlaylists= userDao.getUsersWithPlaylists()

            }
            setContent {
                RoomSamplesTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        UserWithPlaylists(userWithPlaylists)
                    }
                }
            }


        }catch (e: Exception){
            println(e.message.toString())
        }



    }
}

@Composable
fun UserWithPlaylists(userWithPlaylists: List<UserWithPlaylists>) {
    LazyColumn {
        items(userWithPlaylists) { listItem ->
            Column() {
                Text(text = "Hello ${listItem.user}!")
                LazyColumn {
                    items(listItem.playlists) { name ->
                        Column() {
                            Text(text = "Hello ${name.playlistName}!")
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