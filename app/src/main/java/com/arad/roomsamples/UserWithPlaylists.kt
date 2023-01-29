package com.arad.roomsamples

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arad.roomsamples.model.UserWithPlaylistsAndSongs

@Composable
fun UserWithPlaylistsScreen(
    modifier: Modifier = Modifier,
    viewModel: UserWithPlaylistsViewModel = hiltViewModel(),
) {

    val userWithPlaylists by viewModel.userWithPlaylists.collectAsState(
        initial = emptyList()
    )

    val playlistWithSongs by viewModel.playlistWithSongs.collectAsState(
        initial = emptyList()
    )

    val userWithPlaylistsAndSongs by viewModel.userWithPlaylistsAndSongs.collectAsState(
        initial = emptyList()
    )

    LazyColumn(modifier = Modifier.fillMaxWidth(0.5f)) {
        item {
            Card( modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(text = "One-to-Many")
            }

        }
        items(userWithPlaylistsAndSongs) { listItem ->
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
                                Text(text = "${index}-${name.playlist.playlistName}",color = if(index%2  == 1) Color.Red else Color.Blue )
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
        items(userWithPlaylistsAndSongs) { listItem ->
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