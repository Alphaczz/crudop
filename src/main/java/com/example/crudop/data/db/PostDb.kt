package com.example.crudop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.crudop.data.model.PostModel

@Database(entities = [PostModel::class], version = 1, exportSchema = false)
abstract class PostDatabase : RoomDatabase()
{
    abstract fun PostDao():PostDao
}