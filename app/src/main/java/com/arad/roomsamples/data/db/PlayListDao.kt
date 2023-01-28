package com.arad.roomsamples.data.db

import androidx.room.*
import com.arad.roomsamples.model.Playlist

@Dao
interface PlayListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend   fun insertPlayLists(vararg playlist: Playlist)

}