package uz.gita.task_app.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.task_app.data.room.dao.TaskDao
import uz.gita.task_app.data.room.entity.TaskEntity

// Created by Jamshid Isoqov an 8/10/2022
@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        private lateinit var instance: AppDatabase

        fun init(ctx: Context) {
            instance = Room.databaseBuilder(ctx, AppDatabase::class.java, "todo_list.db")
                .allowMainThreadQueries()
                .build()
        }

        fun getInstance() = instance

    }

}