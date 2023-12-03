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
class PostViewModel @Inject constructor(private val repo: postRepo) : ViewModel() {

    private val _postLiveData = MutableLiveData<List<PostModel>>()
    val postLiveData: LiveData<List<PostModel>> get() = _postLiveData
    var post: PostModel=PostModel(
    postid = 0L,
    postTitle = "Sample Title",
    posterDes = "Sample Description",
    author = "Sample Author",
    likes = 0,
    dislike = 0
    )
    init {

        getPost()
    }
    fun savePost(post: PostModel)
    {
        viewModelScope.launch {
            repo.savePost(post)
            Log.i("TAGGY","POST SAVED")
        }
    }
    private suspend fun refreshPostList() {
        val updatedList = repo.getPost()
        _postLiveData.postValue(updatedList!!)
    }
    fun getOneFromDb(postid: Long): LiveData<PostModel> {
        val liveData = MutableLiveData<PostModel>()

        viewModelScope.launch {
            try {
                val post = repo.getOneFromDb(postid)
                post?.let {
                    liveData.postValue(it)
                    Log.i("TAGGY", "POST retrieved ${it.postTitle}")
                }
            } catch (e: Exception) {
                Log.i("TAGGY", "Error retrieving post: ${e.message}")
            }
        }

        return liveData
    }

    fun getPost() {
        viewModelScope.launch {
            try {
                val postList = repo.getPost()
                _postLiveData.postValue(postList!!)
            } catch (e: Exception) {
                Log.i("getPosts",e.message.toString())
            }
        }
    }

    fun updatePost(post: PostModel) {
        viewModelScope.launch {
            try {
                repo.updatePost(post)
                // Optionally, fetch and emit the updated list if needed
                val updatedList = repo.getPost()
                _postLiveData.postValue(updatedList!!)
            } catch (e: Exception) {
                Log.i("updatePost",e.message.toString())
            }
        }
    }

    fun incrementLikes(post: PostModel) {
        viewModelScope.launch {
            try {
                repo.incrementLike(post)
                val updatedList = repo.getPost()
                _postLiveData.postValue(updatedList!!)
                refreshPostList()
            } catch (e: Exception) {
                Log.i("incrementLikes",e.message.toString())
            }
        }
    }

    fun incrementDislikes(post: PostModel) {
        viewModelScope.launch {
            try {
                repo.incrementDislike(post)
                val updatedList = repo.getPost()
                _postLiveData.postValue(updatedList!!)
                refreshPostList()
            } catch (e: Exception) {
                Log.i("getMovies",e.message.toString())
            }
        }
    }

    fun deletePost(postid: Long) {
        viewModelScope.launch {
            try {
                repo.deletePost(postid)
                val updatedList = repo.getPost()
                _postLiveData.postValue(updatedList!!)
                refreshPostList()
            } catch (e: Exception) {
                Log.i("deletePOSTERROR",e.message.toString())
            }
        }
    }
}
