package com.example.crudop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudop.domain.repository.postRepo

class CreatePostViewModelFactory (
    private val postRepo: postRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreatePostViewModel::class.java)) {
            return CreatePostViewModel(postRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
