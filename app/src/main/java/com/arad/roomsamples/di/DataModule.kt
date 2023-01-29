package com.arad.roomsamples.di

import android.content.Context
import com.arad.roomsamples.data.db.PlayListDao
import com.arad.roomsamples.data.db.PlayListDatabase
import com.arad.roomsamples.data.db.SongDao
import com.arad.roomsamples.data.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PlayListDatabase =
        PlayListDatabase.create(context)

    @Provides
    fun provideSongDao(database: PlayListDatabase): SongDao {
        return database.songDao()
    }

    @Provides
    fun provideUserDao(database: PlayListDatabase): UserDao {
        return database.userDao()
    }


    @Provides
    fun providePlayListDao(database: PlayListDatabase): PlayListDao {
        return database.playListDao()
    }
}