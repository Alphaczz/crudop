package com.example.crudop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudop.domain.repository.postRepo


class PostViewModelFactory(
    private val postRepo: postRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(postRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}