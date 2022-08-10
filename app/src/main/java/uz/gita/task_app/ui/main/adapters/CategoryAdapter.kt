package uz.gita.task_app.ui.main.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.task_app.R
import uz.gita.task_app.data.models.CategoryEntity
import uz.gita.task_app.databinding.ListItemCategoryBinding
import uz.gita.task_app.utils.extensions.drawableStringToDrawable
import uz.gita.task_app.utils.extensions.inflate

// Created by Jamshid Isoqov an 8/10/2022
class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categoryList: List<CategoryEntity> = emptyList()
    var selectedPos = 0

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<CategoryEntity>) {
        categoryList = list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class ViewHolder(private val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                selectedPos = adapterPosition
                notifyDataSetChanged()
            }
        }

        fun onBind() {
            val data = categoryList[adapterPosition]
            binding.tvCategory.text = data.categoryName
            binding.imgCategory.setImageResource(
                drawableStringToDrawable(
                    binding.tvCategory.context,
                    data.categoryDrawable
                )
            )
            if (adapterPosition == selectedPos) {
                binding.root.setBackgroundResource(R.drawable.bg_intro_btn_next)
            } else {
                binding.root.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemCategoryBinding.bind(parent.inflate(R.layout.list_item_category)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    override fun getItemCount(): Int = categoryList.size
}