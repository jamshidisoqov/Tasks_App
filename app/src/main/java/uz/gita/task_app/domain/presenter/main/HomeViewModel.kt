package uz.gita.task_app.domain.presenter.main

import androidx.lifecycle.LiveData
import uz.gita.task_app.data.room.entity.TaskEntity

// Created by Jamshid Isoqov an 8/9/2022
interface HomeViewModel {

    val allTasks: LiveData<List<TaskEntity>>

    val openProfileLiveData: LiveData<Unit>

    val openUpdateTaskLiveData:LiveData<TaskEntity>

    val openAddTaskLiveData: LiveData<Unit>

    val openEditDialog:LiveData<TaskEntity>

    val openCalenderLiveData: LiveData<Unit>

    val spinnerPosition:LiveData<Int>

    val openBottomMenu:LiveData<Unit>

    val date: LiveData<String>

    fun clickOpenCalendar()

    fun getTasks(date: String, pos: Int)

    fun openProfile()

    fun updateTask(taskData: TaskEntity)

    fun openCalendar()

    fun editClicked(taskData: TaskEntity)

    fun addTaskClick()

    fun setData(date: String)

    fun setPosition(pod:Int)

    fun openUpdate(taskEntity: TaskEntity)

    fun menuClick()

}