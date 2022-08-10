package uz.gita.task_app.ui.main.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.task_app.databinding.DialogAddHeaderBinding

// Created by Jamshid Isoqov an 8/10/2022
class AddHeaderDialog(ctx: Context) : Dialog(ctx) {

    private var addHeaderListener: ((String, String) -> Unit)? = null

    fun setHeaderListener(block: (String, String) -> Unit) {
        addHeaderListener = block
    }

    private lateinit var binding: DialogAddHeaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogAddHeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.btnAddHeader.setOnClickListener {
            val title = binding.edTaskTitle.text.toString()
            val description = binding.edTaskDescription.text.toString()
            if (title.isNotEmpty()) {
                addHeaderListener?.invoke(title, description)
                dismiss()
            } else {
                binding.edTaskTitle.error = "Required"
            }
        }
    }

}