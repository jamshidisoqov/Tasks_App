package uz.gita.task_app.utils

import androidx.recyclerview.widget.DiffUtil
import uz.gita.task_app.data.room.entity.TaskEntity

// Created by Jamshid Isoqov an 8/10/2022
class TaskItemCallback : DiffUtil.ItemCallback<TaskEntity>() {

    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.description == newItem.description && oldItem.isWorking == newItem.isWorking
    }
}