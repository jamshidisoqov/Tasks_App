package uz.gita.task_app.domain.presenter.main.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.task_app.data.pref.MySharedPreferences
import uz.gita.task_app.data.pref.impl.MySharedPreferencesImpl
import uz.gita.task_app.data.room.entity.TaskEntity
import uz.gita.task_app.domain.presenter.main.HomeViewModel
import uz.gita.task_app.domain.repository.main.impl.HomeRepositoryImpl
import uz.gita.task_app.utils.extensions.getCurrentDate

class HomeViewModelImpl : ViewModel(), HomeViewModel {

    private val repository = HomeRepositoryImpl.getInstance()
    private val sharedPreferences = MySharedPreferencesImpl.getInstance()

    private var _allTasks: MutableLiveData<List<TaskEntity>> = MutableLiveData()
    override val allTasks: LiveData<List<TaskEntity>> = _allTasks

    override val imageLiveData:MutableLiveData<String> = MutableLiveData()

    private var _openProfileLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val openProfileLiveData: LiveData<Unit> = _openProfileLiveData

    private var _openUpdateTaskLiveData: MutableLiveData<TaskEntity> = MutableLiveData()
    override val openUpdateTaskLiveData: LiveData<TaskEntity> = _openUpdateTaskLiveData

    private var _openAddTaskLiveData: MediatorLiveData<Unit> = MediatorLiveData()
    override val openAddTaskLiveData: LiveData<Unit> = _openAddTaskLiveData

    private var _openEditDialog: MutableLiveData<TaskEntity> = MutableLiveData()
    override val openEditDialog: LiveData<TaskEntity> = _openEditDialog

    private var _openCalenderLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val openCalenderLiveData: LiveData<Unit> = _openCalenderLiveData

    private var _spinnerPosition: MutableLiveData<Int> = MutableLiveData(0)
    override val spinnerPosition: LiveData<Int> = _spinnerPosition

    override val openBottomMenu: MutableLiveData<Unit> = MutableLiveData()

    private var _date: MutableLiveData<String> = MutableLiveData(getCurrentDate())
    override val date: LiveData<String> = _date


    override fun clickOpenCalendar() {
        _openCalenderLiveData.postValue(Unit)
    }

    override fun getTasks(date: String, pos: Int) {
        _openAddTaskLiveData.addSource(
            when (pos) {
                0 -> repository.getAllTaskByDate(date)
                1 -> repository.getCompletedTaskByDate(date)
                else -> repository.getInCompletedTaskByDate(date)
            }
        ) {
            _allTasks.postValue(it)
        }
    }

    override fun openProfile() {
        _openProfileLiveData.postValue(Unit)
    }

    override fun getImage() {
        imageLiveData.postValue(sharedPreferences.getImageUri())
    }

    override fun updateTask(taskData: TaskEntity) {
        repository.updateTask(taskData)
    }

    override fun openCalendar() {
        _openCalenderLiveData.postValue(Unit)
    }

    override fun editClicked(taskData: TaskEntity) {
        _openEditDialog.postValue(taskData)
    }

    override fun addTaskClick() {
        _openAddTaskLiveData.postValue(Unit)
    }

    override fun setData(date: String) {
        _date.postValue(date)
        getTasks(date, _spinnerPosition.value!!)
    }

    override fun setPosition(pod: Int) {
        _spinnerPosition.postValue(pod)
        getTasks(_date.value!!, pod)
    }

    override fun openUpdate(taskEntity: TaskEntity) {
       _openUpdateTaskLiveData.postValue(taskEntity)
    }

    override fun menuClick() {
        openBottomMenu.postValue(Unit)
    }


}