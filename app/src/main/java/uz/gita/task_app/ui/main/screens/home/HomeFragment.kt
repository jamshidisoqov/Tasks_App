package uz.gita.task_app.ui.main.screens.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.gita.task_app.R
import uz.gita.task_app.data.room.entity.TaskEntity
import uz.gita.task_app.databinding.FragmentHomeBinding
import uz.gita.task_app.domain.presenter.main.HomeViewModel
import uz.gita.task_app.domain.presenter.main.impl.HomeViewModelImpl
import uz.gita.task_app.ui.main.adapters.TaskAdapter
import uz.gita.task_app.ui.main.dialogs.ChooseCalendarDialog
import uz.gita.task_app.ui.main.dialogs.TaskEditStatusDialog
import uz.gita.task_app.utils.extensions.getCurrentDate

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openAddTaskLiveData.observe(this, addTaskObserver)
        viewModel.openCalenderLiveData.observe(this, openDateObserver)
        viewModel.openEditDialog.observe(this, editDialogListener)
        viewModel.openUpdateTaskLiveData.observe(this, updateTaskObserver)
    }

    private val adapter: TaskAdapter by lazy {
        TaskAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentHomeBinding.bind(view)
        binding.apply {
            bottomNavView.background = null
            bottomNavView.menu.getItem(1).isEnabled = false
            fabAdd.setOnClickListener {
                viewModel.addTaskClick()
            }
            listTodo.adapter = adapter
            tvDate.text = getCurrentDate()
            tvDate.setOnClickListener {
                viewModel.clickOpenCalendar()
            }
            spinnerStatus.onItemSelectedListener = itemSelectedListener
        }
        viewModel.getTasks(viewModel.date.value!!, 0)


        viewModel.allTasks.observe(viewLifecycleOwner, tasksObserver)
        viewModel.date.observe(viewLifecycleOwner, dateObserver)

        adapter.setCheckedListener {
            viewModel.editClicked(it)
        }
        adapter.setItemClickListener {
            viewModel.openUpdate(it)
        }
        adapter.setItemLongClickListener {
            Toast.makeText(requireContext(), it.description, Toast.LENGTH_SHORT).show()
        }
    }

    private val addTaskObserver = Observer<Unit> {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddTodoFragment())
    }

    private val tasksObserver = Observer<List<TaskEntity>> {
        if (it.isEmpty())
            binding.imgPlaceHolder.visibility = View.VISIBLE
        else binding.imgPlaceHolder.visibility = View.INVISIBLE
        adapter.submitList(it)

    }
    private val openDateObserver = Observer<Unit> {
        val dialog = ChooseCalendarDialog(requireContext(), viewModel.date.value!!)
        dialog.show()
        dialog.setDateListener {
            viewModel.setData(it)
        }
    }
    private val dateObserver = Observer<String> {
        binding.tvDate.text = it

    }

    private var itemSelectedListener = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.setPosition(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

    }
    private var editDialogListener = Observer<TaskEntity> {
        val dialog = TaskEditStatusDialog(requireContext(), it.isWorking == 1)
        dialog.show()
        dialog.setEditListener { b ->
            viewModel.updateTask(it.copy(isWorking = 1 - it.isWorking))
        }
    }
    private var updateTaskObserver = Observer<TaskEntity> {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToUpdateTodoFragment(
                it
            )
        )
    }
}