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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries().build()

            val userDao = db.userDao()
            userDao.insertUsers(
                User(1,"Ahmad",41),
                User(2,"Arad",5))

            val playlist = db.playListDao()
            playlist.insertPlayLists(Playlist(1,1,"Pop"),
                Playlist(2,1,"Clasic 1"),
                Playlist(3,2,"Clasic 2"),
                Playlist(4,2,"Clasic 3"))

            var userWithPlaylists : List<UserWithPlaylists> = userDao.getUsersWithPlaylists()

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
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(userWithPlaylists) { listItem ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(size = 1.dp))
                    .border(
                        width = 4.dp,
                        color = Color.DarkGray,
                        shape = CircleShape
                    ).padding(8.dp)

            ) {
                Column(modifier = Modifier
                    .fillMaxHeight().padding(8.dp)) {
                    Text(text = "User: ${listItem.user.name}!", color = Color.DarkGray)
                    LazyRow(modifier = Modifier.fillMaxHeight()) {
                        items(listItem.playlists) { name ->
                            Text(text = " ${name.playlistName}")
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