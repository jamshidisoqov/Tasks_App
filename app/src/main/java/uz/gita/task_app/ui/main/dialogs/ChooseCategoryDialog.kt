package uz.gita.task_app.ui.main.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.task_app.data.models.CategoryEntity
import uz.gita.task_app.databinding.DialogChooseCategoryBinding
import uz.gita.task_app.ui.main.adapters.CategoryAdapter

// Created by Jamshid Isoqov an 8/9/2022
class ChooseCategoryDialog(ctx: Context, private val list: List<CategoryEntity>) : Dialog(ctx) {

    private lateinit var binding: DialogChooseCategoryBinding

    private val adapter: CategoryAdapter by lazy {
        CategoryAdapter()
    }

    private var categoryListener: ((CategoryEntity) -> Unit)? = null

    fun setCategoryClickListener(block: (CategoryEntity) -> Unit) {
        categoryListener = block
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogChooseCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        adapter.submitList(list)
        binding.listCategory.adapter = adapter

        binding.btnChooseCategory.setOnClickListener {
            categoryListener?.invoke(list[adapter.selectedPos])
            dismiss()
        }
    }

}