package com.example.crudop.dataSourceImpl

import android.util.Log
import com.example.crudop.data.dataSource.localDataSource
import com.example.crudop.data.db.PostDao
import com.example.crudop.data.model.PostModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class localDataSourceImp(private val postDao: PostDao):localDataSource
{
    override suspend fun getFromDb(): List<PostModel> {
      return postDao.getAllPost()
    }

    override suspend fun updatePost(post: PostModel) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.updatePost(post)
        }
    }

    override suspend fun saveToDb(post: PostModel) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.savePost(post)
            Log.i("TAGY","postid: ${post.postid}")
        }
    }

    override suspend fun deletePost(postid: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.deletePostById(postid)
        }
    }




    override suspend fun incrementLike(post: PostModel) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.incrementLikes(post.postid)
        }
    }

    override suspend fun incrementDislike(post: PostModel) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.incrementDislikes(post.postid)
        }
     }

    override suspend fun getOneFromDb(postid: Long):PostModel {
        return postDao.getPostById(postid)!!

    }


}
