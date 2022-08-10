package uz.gita.task_app.domain.repository.main.impl

import uz.gita.task_app.data.room.AppDatabase
import uz.gita.task_app.data.room.entity.TaskEntity
import uz.gita.task_app.domain.repository.main.UpdateTaskRepository

// Created by Jamshid Isoqov an 8/9/2022

class UpdateTaskRepositoryImpl : UpdateTaskRepository {

    private val dao = AppDatabase.getInstance().taskDao()

    override fun updateTask(taskData: TaskEntity) = dao.updateTask(taskData)
}