package uz.gita.task_app.ui.main.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.task_app.R
import uz.gita.task_app.databinding.ListItemPriorityBinding
import uz.gita.task_app.utils.extensions.inflate

// Created by Jamshid Isoqov an 8/10/2022
class PriorityAdapter : RecyclerView.Adapter<PriorityAdapter.ViewHolder>() {

    var selectedPos = 0


    @SuppressLint("NotifyDataSetChanged")
    inner class ViewHolder(private val binding: ListItemPriorityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                selectedPos = adapterPosition
                notifyDataSetChanged()
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBind() {
            binding.tvPriority.text = "${adapterPosition + 1}"
            if (adapterPosition == selectedPos) binding.root.setBackgroundResource(R.drawable.bg_intro_btn_next) else
                binding.root.setBackgroundColor(Color.TRANSPARENT)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemPriorityBinding.bind(parent.inflate(R.layout.list_item_priority)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    override fun getItemCount() = 10

}