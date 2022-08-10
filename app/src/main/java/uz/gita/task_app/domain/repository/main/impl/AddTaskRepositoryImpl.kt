package uz.gita.task_app.domain.repository.main.impl

import uz.gita.task_app.data.room.AppDatabase
import uz.gita.task_app.data.room.entity.TaskEntity
import uz.gita.task_app.domain.repository.main.AddTaskRepository

// Created by Jamshid Isoqov an 8/9/2022
class AddTaskRepositoryImpl private constructor(): AddTaskRepository {

    private val dao = AppDatabase.getInstance().taskDao()

    override fun insertTask(taskData: TaskEntity) = dao.insertTask(taskData)

    companion object{
        private lateinit var instance: AddTaskRepository

        fun getInstance(): AddTaskRepository {
            if (!Companion::instance.isInitialized)
                instance = AddTaskRepositoryImpl()
            return instance
        }

    }

}