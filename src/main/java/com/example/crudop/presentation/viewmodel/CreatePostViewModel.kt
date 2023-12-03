package com.example.crudop.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.crudop.data.model.PostModel
import com.example.crudop.domain.repository.postRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(private val repo: postRepo) : ViewModel() {


    suspend fun savePost(post: PostModel)
    {
        viewModelScope.launch {
            repo.savePost(post)
            Log.i("TAGGY","POST SAVED")
        }
    }

}
