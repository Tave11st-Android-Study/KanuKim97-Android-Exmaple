package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.myapplication.dataBase.UserDataBase
import com.example.myapplication.dataBase.table.UserTable
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var userDataBase: UserDataBase
    private val mainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        /* userDataBase 생성 */
        userDataBase =
            Room.databaseBuilder(applicationContext, UserDataBase::class.java, "user_db")
            .build()


        mainBinding.InsertButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                userDataBase.userDao().insertUser(UserTable(0, mainBinding.TextInput.text.toString()))
            }
        }
        mainBinding.SelectButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val userList = withContext(Dispatchers.IO) { userDataBase.userDao().getAll().toString() }

                mainBinding.textView.text = userList
            }
        }
    }

}