package uz.gita.task_app.ui.main.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.task_app.R
import uz.gita.task_app.data.room.entity.TaskEntity
import uz.gita.task_app.databinding.ListItemTodoBinding
import uz.gita.task_app.utils.Constants
import uz.gita.task_app.utils.TaskItemCallback
import uz.gita.task_app.utils.extensions.drawableStringToDrawable
import uz.gita.task_app.utils.extensions.inflate

// Created by Jamshid Isoqov an 8/10/2022
class TaskAdapter : ListAdapter<TaskEntity, TaskAdapter.ViewHolder>(TaskItemCallback()) {

    private var checkListener: ((TaskEntity) -> Unit)? = null
    private var itemClickListener: ((TaskEntity) -> Unit)? = null

    fun setCheckedListener(block: (TaskEntity) -> Unit) {
        checkListener = block
    }

    fun setItemClickListener(block: (TaskEntity) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                rbTodo.setOnCheckedChangeListener { _, _ ->
                    checkListener?.invoke(getItem(adapterPosition))
                }
                root.setOnClickListener {
                    itemClickListener?.invoke(getItem(adapterPosition))
                }
            }

        }

        fun onBind() {
            val data = getItem(adapterPosition)
            binding.apply {
                tvNameTodo.text = data.title
                tvTimeTodo.text = data.time
                val category = Constants.categoryList[data.categoryId - 1]
                tvCategoryTask.text = category.categoryName
                tvPriorityTask.text = data.priority.toString()
                binding.imgCategoryTask.setImageResource(
                    drawableStringToDrawable(
                        binding.tvNameTodo.context,
                        category.categoryDrawable
                    )
                )
                rbTodo.isChecked = data.isWorking == 1
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemTodoBinding.bind(
            parent.inflate(R.layout.list_item_todo)
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}