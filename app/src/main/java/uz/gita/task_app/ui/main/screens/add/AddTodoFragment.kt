package uz.gita.task_app.ui.main.screens.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.gita.task_app.R
import uz.gita.task_app.data.models.CategoryEntity
import uz.gita.task_app.databinding.FragmentAddTodoBinding
import uz.gita.task_app.domain.presenter.main.AddTodoViewModel
import uz.gita.task_app.domain.presenter.main.impl.AddTodoViewModelImpl
import uz.gita.task_app.ui.main.dialogs.*
import uz.gita.task_app.utils.Constants
import uz.gita.task_app.utils.extensions.drawableStringToDrawable

class AddTodoFragment : Fragment(R.layout.fragment_add_todo) {

    private val viewModel: AddTodoViewModel by viewModels<AddTodoViewModelImpl>()
    private lateinit var binding: FragmentAddTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openHeaderDialog.observe(this, openHeaderObserver)
        viewModel.openDateDialog.observe(this, openDateObserver)
        viewModel.openTimeDialog.observe(this, openTimeDialogObserver)
        viewModel.openCategoryDialog.observe(this, openCategoryDialogObserver)
        viewModel.openPriorityDialog.observe(this, openPriorityDialogObserver)
        viewModel.closeLiveData.observe(this, closeObserver)
        viewModel.addTaskLiveData.observe(this, saveObserver)
        viewModel.messageLiveData.observe(this, messageListener)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAddTodoBinding.bind(view)

        viewModel.apply {
            titleLiveData.observe(viewLifecycleOwner, headerObserver)
            dateLiveData.observe(viewLifecycleOwner, dataObserver)
            timeLiveData.observe(viewLifecycleOwner, timeObserver)
            taskPriorityLiveData.observe(viewLifecycleOwner, priorityObserver)
            categoryLiveData.observe(viewLifecycleOwner, categoryObserver)
        }

        binding.apply {
            tvHeader.setOnClickListener {
                viewModel.openHeaderDialog()
            }
            tvDate.setOnClickListener {
                viewModel.openDateDialog()
            }
            tvTime.setOnClickListener {
                viewModel.openTimeDialog()
            }
            tvCategory.setOnClickListener {
                viewModel.openCategoryDialog()
            }
            tvPriority.setOnClickListener {
                viewModel.openPriorityDialog()
            }
            btnAddTask.setOnClickListener {
                viewModel.addClicked()
            }
            imgClose.setOnClickListener {
                viewModel.closedClick()
            }
        }

    }

    private val closeObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

    private val saveObserver = Observer<Unit> {
        viewModel.addTask()
    }

    private val messageListener = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    //title observers
    private val openHeaderObserver = Observer<Unit> {
        val dialog = AddHeaderDialog(requireContext())
        dialog.show()
        dialog.setHeaderListener { s, s1 ->
            viewModel.setHeader(s)
            viewModel.setDescription(s1)
        }
    }
    private val headerObserver = Observer<String> {
        binding.tvHeader.text = it
    }

    //date observers
    private val openDateObserver = Observer<Unit> {
        val dateDialog = ChooseCalendarDialog(requireContext())
        dateDialog.show()
        dateDialog.setDateListener {
            viewModel.setDate(it)
        }
    }
    private val dataObserver = Observer<String> {
        binding.tvDate.text = it
    }

    //time observers
    private val openTimeDialogObserver = Observer<Unit> {
        val dialog = ChooseTimeDialog(requireContext())
        dialog.show()
        dialog.setTimeListener { time ->
            viewModel.setTime(time)
        }
    }
    private val timeObserver = Observer<String> {
        binding.tvTime.text = it
    }

    //category observers
    private val openCategoryDialogObserver = Observer<Unit> {
        val dialog = ChooseCategoryDialog(requireContext(), Constants.categoryList)
        dialog.show()
        dialog.setCategoryClickListener {
            viewModel.setCategory(it)
        }
    }
    private val categoryObserver = Observer<CategoryEntity> {
        binding.tvCategory.text = it.categoryName
        binding.tvCategory.setCompoundDrawablesWithIntrinsicBounds(
            drawableStringToDrawable(requireContext(),it.categoryDrawable),
            0,
            0,
            0
        )
    }

    //priority observer
    private val openPriorityDialogObserver = Observer<Unit> {
        val dialog = ChoosePriorityDialog(requireContext())
        dialog.show()
        dialog.setPriorityListener {
            viewModel.setPriority(it + 1)
        }
    }
    private val priorityObserver = Observer<Int> {
        binding.tvPriority.text = "$it"
    }


}