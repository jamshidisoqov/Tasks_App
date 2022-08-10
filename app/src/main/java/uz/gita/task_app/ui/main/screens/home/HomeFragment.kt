package uz.gita.task_app.ui.main.screens.home

import android.os.Bundle
import android.view.View
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
import uz.gita.task_app.utils.extensions.getCurrentDate

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openAddTaskLiveData.observe(this, addTaskObserver)
        viewModel.openCalenderLiveData.observe(this, openDateObserver)
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
        }
        viewModel.getTasks(viewModel.date.value!!, 0)


        viewModel.allTasks.observe(viewLifecycleOwner, tasksObserver)
        viewModel.date.observe(viewLifecycleOwner, dateObserver)
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
        val dialog = ChooseCalendarDialog(requireContext())
        dialog.show()
        dialog.setDateListener {
            viewModel.setData(it)
        }
    }
    private val dateObserver = Observer<String> {
        binding.tvDate.text = it
    }
}