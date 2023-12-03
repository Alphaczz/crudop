package com.example.crudop.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.crudop.data.db.PostDao
import com.example.crudop.data.db.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class databaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): PostDatabase {
        return Room.databaseBuilder(context,
            PostDatabase::class.java,"postdb")
            .build()
    }
    @Singleton
    @Provides
    fun providePostDao(postDatabase: PostDatabase):PostDao{
        return  postDatabase.PostDao()
    }
}