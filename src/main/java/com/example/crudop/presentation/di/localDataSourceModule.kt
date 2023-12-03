package com.example.crudop.presentation.di

import com.example.crudop.data.dataSource.localDataSource
import com.example.crudop.data.db.PostDao
import com.example.crudop.dataSourceImpl.localDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class localDataModule()
{
    @Singleton
    @Provides
    fun provideLocalModule(dao: PostDao):localDataSource
    {
        return localDataSourceImp(dao)
    }
}