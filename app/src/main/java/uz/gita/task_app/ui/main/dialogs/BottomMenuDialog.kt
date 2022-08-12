package uz.gita.task_app.ui.main.dialogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.task_app.MainActivity
import uz.gita.task_app.R
import uz.gita.task_app.databinding.DialogMenuBottomBinding
import uz.gita.task_app.domain.presenter.main.BottomMenuViewModel
import uz.gita.task_app.domain.presenter.main.impl.BottomMenuViewModelImpl
import uz.gita.task_app.utils.Constants

// Created by Jamshid Isoqov an 8/12/2022
class BottomMenuDialog : BottomSheetDialogFragment() {


    private lateinit var binding: DialogMenuBottomBinding

    private val viewModel: BottomMenuViewModel by viewModels<BottomMenuViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogMenuBottomBinding.inflate(inflater, container, false)
        viewModel.aboutUsLiveData.observe(this, aboutObserver)
        viewModel.shareAppLiveData.observe(this, shareAppObserver)
        viewModel.supportUsLiveData.observe(this, supportObserver)

        binding.apply {
            tvAboutUs.setOnClickListener { viewModel.aboutClick() }
            tvSupportUs.setOnClickListener { viewModel.supportClick() }
            tvShareApp.setOnClickListener { viewModel.shareClick() }
        }
        return binding.root
    }

    private val supportObserver = Observer<Unit> {
        dismiss()
        Constants.goToPlayMarket(activity as MainActivity)
    }

    private val shareAppObserver = Observer<Unit> {
        dismiss()
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "UpTodo")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "link: https://play.google.com/store/apps/details?id=uz.gita.logicwordio"
        )
        startActivity(Intent.createChooser(intent, "UpTodo"))
    }

    private val aboutObserver = Observer<Unit> {
        dismiss()
        findNavController().navigate(R.id.aboutFragment)
    }

}