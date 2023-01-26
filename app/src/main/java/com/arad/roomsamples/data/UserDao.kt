package com.arad.roomsamples.data

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: User)

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithPlaylists(): List<UserWithPlaylists>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithPlaylistsAndSongs(): List<UserWithPlaylistsAndSongs>
}