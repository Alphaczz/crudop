package com.example.crudop.data.dataSource

import com.example.crudop.data.model.PostModel

interface localDataSource {
    suspend fun getFromDb():List<PostModel>

    suspend fun updatePost(post: PostModel)
    suspend fun saveToDb(post:PostModel)
    suspend fun deletePost(postid: Long)
    suspend fun incrementLike(post: PostModel)
    suspend fun incrementDislike(post: PostModel)
    suspend fun getOneFromDb(postid:Long):PostModel

}