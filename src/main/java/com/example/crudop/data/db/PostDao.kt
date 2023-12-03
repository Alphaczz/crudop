package com.example.crudop.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.crudop.data.model.PostModel

@Dao
interface PostDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePost(post: PostModel)

    @Query("Select * from post_model")
    suspend fun getAllPost():List<PostModel>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePost(post: PostModel)

    @Query("DELETE FROM post_model WHERE postid = :postid")
    suspend fun deletePostById(postid: Long)

    @Query("UPDATE post_model SET likes = likes + 1 WHERE postid = :entityId")
    fun incrementLikes(entityId: Long)

    @Query("UPDATE post_model SET dislike = dislike + 1 WHERE postid = :entityId")
    fun incrementDislikes(entityId: Long)

    @Query("SELECT * FROM post_model WHERE postid = :postId")
    suspend fun getPostById(postId: Long): PostModel?

}