package uz.gita.task_app.domain.repository.main

import androidx.lifecycle.LiveData
import uz.gita.task_app.data.room.entity.TaskEntity

// Created by Jamshid Isoqov an 8/9/2022
interface HomeRepository {

    fun updateTask(taskData: TaskEntity)

    fun deleteTask(taskData: TaskEntity)

    fun getAllTaskByDate(date: String): LiveData<List<TaskEntity>>

    fun getCompletedTaskByDate(date: String): LiveData<List<TaskEntity>>

    fun getInCompletedTaskByDate(date: String): LiveData<List<TaskEntity>>

}