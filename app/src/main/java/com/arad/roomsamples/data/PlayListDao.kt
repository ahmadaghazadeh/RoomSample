package com.arad.roomsamples.data

import androidx.room.*

@Dao
interface PlayListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayLists(vararg playlist: Playlist)

}