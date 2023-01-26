package com.arad.roomsamples.data

import androidx.room.*

@Dao
interface PlayListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend   fun insertPlayLists(vararg playlist: Playlist)

}