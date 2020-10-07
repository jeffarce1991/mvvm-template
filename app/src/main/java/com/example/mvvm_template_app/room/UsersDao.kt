package com.example.mvvm_template_app.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_template_app.models.User

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: User): Long

    @Query("SELECT * FROM users")
    suspend fun get(): MutableList<User>
}
