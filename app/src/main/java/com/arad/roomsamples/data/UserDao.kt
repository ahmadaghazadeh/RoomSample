package com.arad.roomsamples.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend   fun insertUsers(vararg users: User)

    @Transaction
    @Query("SELECT * FROM User")
    suspend   fun getUsersWithPlaylists(): List<UserWithPlaylists>

    @Transaction
    @Query("SELECT * FROM User")
    suspend   fun getUsersWithPlaylistsAndSongs(): List<UserWithPlaylistsAndSongs>

    @Query("SELECT * from user WHERE userId = :userId")
    fun loadUser(userId: Long): Flow<List<User>>
}