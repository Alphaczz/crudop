package com.example.crudop.domain.util

import com.example.crudop.data.model.PostModel
import java.util.UUID


    fun generateUniqueId(): Long {
        val uuid = UUID.randomUUID()
        return uuid.leastSignificantBits
    }
    fun createPost( postTitle:String,
                    posterDes:String,
                    author:String,
    ): PostModel {
        return PostModel(postid = generateUniqueId() , postTitle=postTitle, posterDes = posterDes, author = author, likes = 0, dislike = 0)
    }
