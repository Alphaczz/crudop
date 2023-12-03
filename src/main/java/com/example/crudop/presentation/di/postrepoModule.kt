package com.example.crudop.presentation.di

import com.example.crudop.data.dataSource.localDataSource
import com.example.crudop.domain.repository.postRepo
import com.example.crudop.domain.repository.postRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PostRepoModule {

    @Provides
    fun providePostRepo(localDataSource: localDataSource): postRepo {
        return postRepoImpl(localDataSource)
    }
}