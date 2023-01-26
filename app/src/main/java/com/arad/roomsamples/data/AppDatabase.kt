package com.arad.roomsamples.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Playlist::class,Song::class,PlaylistSongCrossRef::class],
    exportSchema = true,
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun playListDao(): PlayListDao
    abstract fun SongDao(): SongDao
}