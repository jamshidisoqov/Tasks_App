package uz.gita.task_app.domain.repository.main.impl

import androidx.lifecycle.LiveData
import uz.gita.task_app.data.room.AppDatabase
import uz.gita.task_app.data.room.entity.TaskEntity
import uz.gita.task_app.domain.repository.main.HomeRepository

// Created by Jamshid Isoqov an 8/9/2022
class HomeRepositoryImpl private constructor() : HomeRepository {

    private val dao = AppDatabase.getInstance().taskDao()

    override fun updateTask(taskData: TaskEntity) = dao.updateTask(taskData)

    override fun deleteTask(taskData: TaskEntity) = dao.deleteTask(taskData)

    override fun getAllTaskByDate(date: String): LiveData<List<TaskEntity>> =
        dao.getTasks(date)

    override fun getCompletedTaskByDate(date: String): LiveData<List<TaskEntity>>  =
        dao.getCompletedTasks(date)

    override fun getInCompletedTaskByDate(date: String): LiveData<List<TaskEntity>> =
        dao.getNotCompletedTasks(date)


    companion object {
        private lateinit var instance: HomeRepository

        fun getInstance(): HomeRepository {
            if (!Companion::instance.isInitialized) {
                instance = HomeRepositoryImpl()
            }
            return instance
        }

    }
}