package com.example.crudop.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Post_Model")
data class PostModel(
    @PrimaryKey(autoGenerate = true)
    val postid:Long,
    val postTitle:String,
    val posterDes:String,
    val author:String,
    val likes:Int,
    val dislike:Int

)