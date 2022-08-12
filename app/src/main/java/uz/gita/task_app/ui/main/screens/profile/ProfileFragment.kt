package uz.gita.task_app.ui.main.screens.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.gita.task_app.MainActivity
import uz.gita.task_app.R
import uz.gita.task_app.databinding.FragmentProfileBinding
import uz.gita.task_app.domain.presenter.main.ProfileViewModel
import uz.gita.task_app.domain.presenter.main.impl.ProfileViewModelImpl
import uz.gita.task_app.ui.main.dialogs.ChangeNameDialog
import uz.gita.task_app.utils.Constants

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.changeNameLiveData.observe(this, changeNameObserver)
        viewModel.changeImageLiveData.observe(this, changeImageObserver)
        viewModel.aboutUsLiveData.observe(this, aboutObserver)
        viewModel.contactLiveData.observe(this, contactObserver)
        viewModel.supportLiveData.observe(this, supportObserver)
        viewModel.backLiveData.observe(this, backListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProfileBinding.bind(view)
        binding.apply {

            tvChangeUserName.setOnClickListener {
                viewModel.changeName()
            }
            tvChangeImageIcon.setOnClickListener {
                viewModel.changeImage()
            }
            tvAboutUs.setOnClickListener {
                viewModel.aboutClicked()
            }
            tvHelp.setOnClickListener {
                viewModel.helpClicked()
            }
            tvSupportUs.setOnClickListener {
                viewModel.supportClicked()
            }
            imgClose.setOnClickListener {
                viewModel.backClick()
            }

        }
        viewModel.nameLiveData.observe(viewLifecycleOwner, nameObserver)
    }

    private val nameObserver = Observer<String> {
        binding.tvUserName.text = it
    }

    private val changeNameObserver = Observer<Unit> {
        val dialog = ChangeNameDialog(requireContext(), viewModel.nameLiveData.value!!)
        dialog.show()
        dialog.setChangeListener {
            viewModel.setName(it)
        }
    }

    private val backListener = Observer<Unit> {
        findNavController().navigateUp()
    }

    private val changeImageObserver = Observer<Unit> {
        Toast.makeText(requireContext(), "is working", Toast.LENGTH_SHORT).show()
    }

    private val aboutObserver = Observer<Unit> {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAboutFragment())
    }

    private val contactObserver = Observer<Unit> {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "isoqovjamshid01@gmail.com", null))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        startActivity(Intent.createChooser(intent, "Choose an Email client :"))
    }

    private val supportObserver = Observer<Unit> {
        Constants.goToPlayMarket(activity as MainActivity)
    }

}