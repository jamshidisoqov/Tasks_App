package uz.gita.task_app.ui.main.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.task_app.databinding.DialogChoosePriorityBinding
import uz.gita.task_app.ui.main.adapters.PriorityAdapter

// Created by Jamshid Isoqov an 8/9/2022
class ChoosePriorityDialog(ctx: Context, private val priority: Int) : Dialog(ctx) {
    private lateinit var binding: DialogChoosePriorityBinding
    private var priorityListener: ((Int) -> Unit)? = null
    private val adapter: PriorityAdapter by lazy {
        PriorityAdapter()
    }

    fun setPriorityListener(block: (Int) -> Unit) {
        priorityListener = block
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = DialogChoosePriorityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        adapter.selectedPos = priority-1
        adapter.notifyDataSetChanged()
        binding.listPriority.adapter = adapter
        binding.btnChooseCategory.setOnClickListener {
            priorityListener?.invoke(adapter.selectedPos)
            dismiss()
        }

    }

}