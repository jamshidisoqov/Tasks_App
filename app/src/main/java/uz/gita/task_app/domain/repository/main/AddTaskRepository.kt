package uz.gita.task_app.domain.repository.main

import uz.gita.task_app.data.room.entity.TaskEntity

// Created by Jamshid Isoqov an 8/9/2022
interface AddTaskRepository {
    fun insertTask(taskData: TaskEntity)
}