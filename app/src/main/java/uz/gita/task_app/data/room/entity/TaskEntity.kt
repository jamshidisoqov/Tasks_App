package uz.gita.task_app.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Created by Jamshid Isoqov an 8/10/2022
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    @ColumnInfo(name = "is_working")
    val isWorking: Int,
    val priority: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int
)
