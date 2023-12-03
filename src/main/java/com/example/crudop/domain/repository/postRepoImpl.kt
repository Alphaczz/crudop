package com.example.crudop.domain.repository

import android.util.Log
import com.example.crudop.data.dataSource.localDataSource
import com.example.crudop.data.model.PostModel
import javax.inject.Inject

class postRepoImpl @Inject constructor(
   private  val localDataSource: localDataSource
): postRepo {
    override suspend fun savePost(postModel: PostModel) {
        localDataSource.saveToDb(postModel)
    }

    override suspend fun getPost(): List<PostModel>? {
       return  getPostFromRoom()
    }

    override suspend fun updatePost(postModel: PostModel) {
        localDataSource.updatePost(postModel)
    }

    override suspend fun incrementLike(postModel: PostModel) {
        localDataSource.incrementLike(postModel)
    }

    override suspend fun incrementDislike(postModel: PostModel) {
       localDataSource.incrementDislike(postModel)
    }

    override suspend fun deletePost(postid: Long) {
        localDataSource.deletePost(postid)
    }

    override suspend fun getOneFromDb(postid: Long):PostModel {
        return localDataSource.getOneFromDb(postid)
    }




    suspend fun getPostFromRoom():List<PostModel>
    {
        lateinit var postList: List<PostModel>
        try {
            postList= localDataSource.getFromDb()
        }
        catch (exeception:java.lang.Exception)
        {
            Log.i("GetPostFromRoom",exeception.message.toString())
        }
        return postList
    }

}