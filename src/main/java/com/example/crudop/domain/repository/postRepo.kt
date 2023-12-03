package com.example.crudop.domain.repository

import com.example.crudop.data.model.PostModel

interface postRepo
{
    suspend fun savePost(postModel: PostModel)
    suspend fun getPost():List<PostModel>?
    suspend fun  updatePost(postModel: PostModel)
    suspend fun incrementLike(postModel: PostModel)
    suspend fun incrementDislike( postModel: PostModel)
    suspend fun deletePost(postId:Long)
    suspend fun getOneFromDb(postid:Long):PostModel?

}