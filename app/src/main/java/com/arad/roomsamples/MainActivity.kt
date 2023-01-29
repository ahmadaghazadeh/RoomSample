package com.arad.roomsamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.arad.roomsamples.data.PlayListRepository
import com.arad.roomsamples.model.*
import com.arad.roomsamples.ui.theme.RoomSamplesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
//TODO : refactor project's structure like  https://github.com/alexmamo/RoomJetpackCompose
//TODO : refactor project's dependencies like  https://github.com/alexmamo/RoomJetpackCompose
//TODO : obey SonarLint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val exampleViewModel: UserWithPlaylistsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {


            val userWithPlaylists: ArrayList<UserWithPlaylists> = arrayListOf()
            val playlistWithSongs: ArrayList<PlaylistWithSongs> = arrayListOf()
            val userWithPlaylistsAndSongs: ArrayList<UserWithPlaylistsAndSongs> = arrayListOf()

            withContext(Dispatchers.Main) {
                setContent {
                    RoomSamplesTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.background
                        ) {
                            UserWithPlaylistsScreen()
                        }
                    }
                }
            }

//            try {
//                repository.getUsersWithPlaylists()
//                    .flowOn(Dispatchers.IO)
//                    .catch { e ->
//                        e.message
//                    }.onEach {
//                        userWithPlaylists.addAll(it)
//
//                    }
//                    .launchIn(lifecycleScope)
//
//                repository.getPlaylistsWithSongs().collect {
//                    println(it)
//                    playlistWithSongs.addAll(it)
//                }
//
//                repository.getUsersWithPlaylistsAndSongs().collect {
//                    println(it)
//                    userWithPlaylistsAndSongs.addAll(it)
//                }
//            }catch (ex:Exception){
//                ex.message
//            }
        }
    }
    @Composable
    fun GameScreen(
        modifier: Modifier = Modifier,
    ) {
//    LazyColumn(modifier = Modifier.fillMaxWidth(0.5f)) {
//        item {
//            Card( modifier = Modifier
//                .fillMaxHeight()
//                .fillMaxWidth()
//                .padding(8.dp)) {
//                Text(text = "One-to-Many")
//            }
//
//        }
//        items(userWithPlaylists) { listItem ->
//            Card(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .fillMaxWidth()
//                    .padding(8.dp)
//
//            ) {
//                Column(modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//                    .padding(8.dp)) {
//                    Text(text = "User: ${listItem.user.name}", color = Color.DarkGray)
//                    LazyRow(modifier = Modifier.fillMaxHeight()) {
//                        itemsIndexed(listItem.playlists) { index,name ->{}
//                            Card(modifier = Modifier
//                                .fillMaxHeight()
//                                .fillMaxWidth()
//                                .padding(8.dp)
//                            ) {
//                                Text(text = "${index}-${name.playlistName}",color = if(index%2  == 1) Color.Red else Color.Blue )
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//
//        item {
//            Divider(color = Color.Black)
//            Card( modifier = Modifier
//                .fillMaxHeight()
//                .fillMaxWidth()
//                .padding(8.dp)) {
//                Text(text = "Many-to-Many")
//            }
//        }
//        items(playlistWithSongs) { listItem ->
//            Card(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .fillMaxWidth()
//                    .padding(8.dp)
//
//            ) {
//                Column(modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//                    .padding(8.dp)) {
//                    Text(text = "Play List: ${listItem.playlist.playlistName}", color = Color.DarkGray)
//                    LazyRow(modifier = Modifier.fillMaxHeight()) {
//                        itemsIndexed(listItem.songs) { index,name ->{}
//                            Card(modifier = Modifier
//                                .fillMaxHeight()
//                                .fillMaxWidth()
//                                .padding(8.dp)
//                            ) {
//                                Text(text = "${index}-${name.songName}",color = if(index%2  == 1) Color.Red else Color.Blue )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        item {
//            Divider(color = Color.Black)
//            Card( modifier = Modifier
//                .fillMaxHeight()
//                .fillMaxWidth()
//                .padding(8.dp)) {
//                Text(text = "Nested")
//            }
//        }
//        items(UserWithPlaylistsAndSongs) { listItem ->
//            Card(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .fillMaxWidth()
//                    .padding(8.dp)
//
//            ) {
//                Column(modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//                    .padding(8.dp)) {
//                    Text(text = "Play List: ${listItem.user.name}", color = Color.DarkGray)
//                    LazyRow(modifier = Modifier.fillMaxHeight()) {
//                        itemsIndexed(listItem.playlists) { index,playList ->{}
//                            Card(modifier = Modifier
//                                .fillMaxHeight()
//                                .fillMaxWidth()
//                                .padding(8.dp)
//                            ) {
//                                Column() {
//                                    Text(text = "Play List: ${playList.playlist.playlistName}", color = Color.DarkGray)
//                                }
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
        //}
    }
}


