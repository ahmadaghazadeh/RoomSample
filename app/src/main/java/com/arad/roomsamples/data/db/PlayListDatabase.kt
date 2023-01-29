package com.arad.roomsamples.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arad.roomsamples.model.Playlist
import com.arad.roomsamples.model.PlaylistSongCrossRef
import com.arad.roomsamples.model.Song
import com.arad.roomsamples.model.User

private const val DB_NAME = "playlist_database"

@Database(entities = [User::class, Playlist::class, Song::class, PlaylistSongCrossRef::class],
    exportSchema = true,
    version = 1)
abstract class PlayListDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun playListDao(): PlayListDao
    abstract fun songDao(): SongDao

    companion object {
        fun create(context: Context): PlayListDatabase {

            return Room.databaseBuilder(
                context,
                PlayListDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}