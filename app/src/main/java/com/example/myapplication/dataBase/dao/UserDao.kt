package com.example.myapplication.dataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.dataBase.table.UserTable

@Dao
interface UserDao {
    @Query("select * from user_table")
    fun getAll(): List<UserTable>

    @Insert
    fun insertUser(user: UserTable)
}