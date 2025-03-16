package com.example.smartstock.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.smartstock.database.entities.User

@Dao
interface UserDao {


    //Insertar Usuario

    @Insert
    suspend fun insert_user(user: User)

    @Query("SELECT * FROM users")

    suspend fun getAllUsers():List<User>

    @Delete
    suspend fun delete_user()
}