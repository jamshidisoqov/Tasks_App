package uz.gita.task_app.ui.main.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.task_app.databinding.DialogChangeNameBinding

// Created by Jamshid Isoqov an 8/12/2022
class ChangeNameDialog(ctx: Context, private val name: String) : Dialog(ctx) {

    private lateinit var binding: DialogChangeNameBinding
    private var changeListener: ((String) -> Unit)? = null
    fun setChangeListener(block: (String) -> Unit) {
        changeListener = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogChangeNameBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)

        binding.edName.setText(name)

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnEdit.setOnClickListener {
            val name = binding.edName.text.toString()
            if (name.isEmpty()) {
                binding.btnEdit.error = "Name empty"
            } else {
                changeListener?.invoke(name)
                dismiss()
            }
        }

    }

}