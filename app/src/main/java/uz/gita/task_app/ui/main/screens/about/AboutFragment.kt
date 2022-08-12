package uz.gita.task_app.ui.main.screens.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.gita.task_app.R
import uz.gita.task_app.databinding.FragmentAboutBinding
import uz.gita.task_app.domain.presenter.main.AboutViewModel

class AboutFragment : Fragment(R.layout.fragment_about) {


    private val viewModel: AboutViewModel by viewModels()
    private lateinit var binding: FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this, backObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAboutBinding.bind(view)
        binding.imgClose.setOnClickListener {
            viewModel.onBack()
        }
    }

    private val backObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

}