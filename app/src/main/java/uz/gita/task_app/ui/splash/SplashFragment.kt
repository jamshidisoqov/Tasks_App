package uz.gita.task_app.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.gita.task_app.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openMainLiveData.observe(this, openMainObserver)
        viewModel.openIntroLiveData.observe(this, openSplashObserver)

    }

    private val openMainObserver = Observer<Unit> {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }
    private val openSplashObserver = Observer<Unit> {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToIntroFragment())
    }

}