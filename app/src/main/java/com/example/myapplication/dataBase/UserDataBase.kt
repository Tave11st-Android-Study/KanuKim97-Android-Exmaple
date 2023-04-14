package com.example.myapplication.dataBase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.AndroidApp
import com.example.myapplication.dataBase.dao.UserDao
import com.example.myapplication.dataBase.table.UserTable

@Database(entities = [UserTable::class], version = 1, exportSchema = false)
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}