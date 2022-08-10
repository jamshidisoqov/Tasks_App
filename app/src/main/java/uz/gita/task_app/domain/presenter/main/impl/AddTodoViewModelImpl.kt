package uz.gita.task_app.domain.presenter.main.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.task_app.data.models.CategoryEntity
import uz.gita.task_app.data.room.entity.TaskEntity
import uz.gita.task_app.domain.presenter.main.AddTodoViewModel
import uz.gita.task_app.domain.repository.main.AddTaskRepository
import uz.gita.task_app.domain.repository.main.impl.AddTaskRepositoryImpl
import uz.gita.task_app.utils.Constants
import uz.gita.task_app.utils.extensions.getCurrentDate
import uz.gita.task_app.utils.extensions.getCurrentTime

class AddTodoViewModelImpl : ViewModel(), AddTodoViewModel {

    private val repository: AddTaskRepository = AddTaskRepositoryImpl.getInstance()

    private var _titleLiveData: MutableLiveData<String> = MutableLiveData("Do math homework")
    override val titleLiveData: LiveData<String> = _titleLiveData

    private var _descriptionLiveData: MutableLiveData<String> = MutableLiveData("Do math homework")
    override val descriptionLiveData: LiveData<String> = _descriptionLiveData

    private var _dateLiveDta: MutableLiveData<String> = MutableLiveData(getCurrentDate())
    override val dateLiveData: LiveData<String> = _dateLiveDta

    private var _timeLiveData: MutableLiveData<String> = MutableLiveData(getCurrentTime())
    override val timeLiveData: LiveData<String> = _timeLiveData

    private var _categoryLiveData: MutableLiveData<CategoryEntity> =
        MutableLiveData(Constants.categoryList[0])
    override val categoryLiveData: LiveData<CategoryEntity> = _categoryLiveData

    private var _taskPriorityLiveData: MutableLiveData<Int> = MutableLiveData(1)
    override val taskPriorityLiveData: LiveData<Int> = _taskPriorityLiveData

    private var _closedLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val closeLiveData: LiveData<Unit> = _closedLiveData

    private var _editLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val editLiveData: LiveData<Unit> = _editLiveData

    private var _addTaskLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val addTaskLiveData: LiveData<Unit> = _addTaskLiveData

    private var _openHeaderDialog: MutableLiveData<Unit> = MutableLiveData()
    override val openHeaderDialog: LiveData<Unit> = _openHeaderDialog

    private var _openDateDialog: MutableLiveData<Unit> = MutableLiveData()
    override val openDateDialog: LiveData<Unit> = _openDateDialog

    private var _openTimeDialog: MutableLiveData<Unit> = MutableLiveData()
    override val openTimeDialog: LiveData<Unit> = _openTimeDialog

    private var _openCategoryDialog: MutableLiveData<Unit> = MutableLiveData()
    override val openCategoryDialog: LiveData<Unit> = _openCategoryDialog

    private var _openPriorityDialog: MutableLiveData<Unit> = MutableLiveData()
    override val openPriorityDialog: LiveData<Unit> = _openPriorityDialog

    private var _messageLiveData: MutableLiveData<String> = MutableLiveData()
    override val messageLiveData: LiveData<String> = _messageLiveData


    override fun openHeaderDialog() {
        _openHeaderDialog.postValue(Unit)
    }

    override fun openDateDialog() {
        _openDateDialog.postValue(Unit)
    }

    override fun openTimeDialog() {
        _openTimeDialog.postValue(Unit)
    }

    override fun openCategoryDialog() {
        _openCategoryDialog.postValue(Unit)
    }

    override fun openPriorityDialog() {
        _openPriorityDialog.postValue(Unit)
    }

    override fun closedClick() {
        _closedLiveData.postValue(Unit)
    }

    override fun addClicked() {
        _addTaskLiveData.postValue(Unit)
    }

    override fun addTask() {
        val title = titleLiveData.value!!
        val description = descriptionLiveData.value!!
        val date = dateLiveData.value!!
        val time = timeLiveData.value!!
        val category = categoryLiveData.value!!
        val priority = taskPriorityLiveData.value!!
        repository.insertTask(
            TaskEntity(
                0, title, description, date, time, 0, priority, category.id
            )
        )
        _messageLiveData.postValue("Successfully added")
        _titleLiveData.postValue("Do math homework")
        _descriptionLiveData.postValue("Do math homework")
        _dateLiveDta.postValue(getCurrentDate())
        _timeLiveData.postValue(getCurrentTime())
        _taskPriorityLiveData.postValue(1)
    }

    override fun setHeader(title: String) {
        _titleLiveData.postValue(title)
    }

    override fun setDescription(description: String) {
        _descriptionLiveData.postValue(description)
    }

    override fun setDate(date: String) {
        _dateLiveDta.postValue(date)
    }

    override fun setTime(time: String) {
        _timeLiveData.postValue(time)
    }

    override fun setPriority(priority: Int) {
        _taskPriorityLiveData.postValue(priority)
    }

    override fun setCategory(categoryEntity: CategoryEntity) {
        _categoryLiveData.postValue(categoryEntity)
    }

}