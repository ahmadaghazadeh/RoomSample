package com.arad.roomsamples.data.db

import androidx.room.*
import com.arad.roomsamples.model.User
import com.arad.roomsamples.model.UserWithPlaylists
import com.arad.roomsamples.model.UserWithPlaylistsAndSongs
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend   fun insertUsers(vararg users: User)

    @Transaction
    @Query("SELECT * FROM User")
       fun getUsersWithPlaylists(): Flow<List<UserWithPlaylists>>

    @Transaction
    @Query("SELECT * FROM User")
      fun getUsersWithPlaylistsAndSongs(): Flow<List<UserWithPlaylistsAndSongs>>

    @Query("SELECT * from user ")
    fun loadUsers(): Flow<List<User>>
}