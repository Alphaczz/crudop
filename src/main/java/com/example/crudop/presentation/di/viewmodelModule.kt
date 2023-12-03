package com.example.crudop.presentation.di

import com.example.crudop.domain.repository.postRepo
import com.example.crudop.presentation.viewmodel.CreatePostViewModel
import com.example.crudop.presentation.viewmodel.CreatePostViewModelFactory
import com.example.crudop.presentation.viewmodel.PostViewModel
import com.example.crudop.presentation.viewmodel.PostViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ViewModelModule {

    @Provides
    fun providePostViewModel(postRepo: postRepo): PostViewModel {
        return PostViewModel(postRepo)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(
        postRepo: postRepo
    ): PostViewModelFactory {
        return PostViewModelFactory(
            postRepo
        )
    }


}
@Module
@InstallIn(ViewModelComponent::class)
object CreatePostViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideCreatePostViewModel(repository:postRepo): CreatePostViewModel {
        return CreatePostViewModel(repository)
    }

    @Provides
    @Singleton
    fun provideCreatePostViewModelFactory(
        postRepo: postRepo
    ): CreatePostViewModelFactory {
        return CreatePostViewModelFactory(
            postRepo
        )
    }
}

