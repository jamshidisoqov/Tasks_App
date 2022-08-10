package uz.gita.task_app.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.task_app.data.room.entity.TaskEntity

// Created by Jamshid Isoqov an 8/10/2022
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(taskEntity: TaskEntity)

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT*FROM tasks WHERE date=:date")
    fun getTasks(date:String): LiveData<List<TaskEntity>>

    @Query("SELECT*FROM tasks WHERE is_working AND date=:date")
    fun getCompletedTasks(date:String):LiveData<List<TaskEntity>>

    @Query("SELECT*FROM tasks WHERE NOT is_working AND date=:date")
    fun getNotCompletedTasks(date:String):LiveData<List<TaskEntity>>

}