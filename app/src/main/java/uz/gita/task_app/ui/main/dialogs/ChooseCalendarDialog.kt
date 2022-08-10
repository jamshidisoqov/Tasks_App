package uz.gita.task_app.ui.main.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import uz.gita.task_app.databinding.DialogChooseCalendarBinding
import java.time.Month

// Created by Jamshid Isoqov an 8/9/2022
class ChooseCalendarDialog(ctx: Context) : Dialog(ctx) {
    private var dateListener: ((String) -> Unit)? = null
    private lateinit var binding: DialogChooseCalendarBinding
    private var day = 0
    private var month = 0
    private var year = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogChooseCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.calendarView.date = System.currentTimeMillis()
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            day = dayOfMonth
            this.month = month
            this.year = year
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnSave.setOnClickListener {
            val builder = StringBuilder()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.append("${Month.of(month+1)} $day,$year")
            } else {
                builder.append("${Month.values()[month+1]} $day,$year")
            }
            dateListener?.invoke(builder.toString())
            dismiss()
        }
    }

    fun setDateListener(block: (String) -> Unit) {
        dateListener = block
    }

}