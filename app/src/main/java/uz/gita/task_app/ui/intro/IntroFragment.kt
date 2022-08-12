package uz.gita.task_app.ui.intro

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import uz.gita.task_app.R
import uz.gita.task_app.data.pref.impl.MySharedPreferencesImpl
import uz.gita.task_app.databinding.FragmentIntroBinding
import uz.gita.task_app.ui.intro.adapters.PagerAdapter


class IntroFragment : Fragment(R.layout.fragment_intro) {

    private val adapter: PagerAdapter by lazy {
        PagerAdapter(requireActivity())
    }

    private lateinit var binding: FragmentIntroBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentIntroBinding.bind(view)
        binding.viewPagerIntro.adapter = adapter

        binding.viewPagerIntro.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 0) {
                    binding.btnIntroBack.apply {
                        visibility = View.INVISIBLE
                        isEnabled = false
                    }
                } else {
                    binding.btnIntroBack.apply {
                        visibility = View.VISIBLE
                        isEnabled = true
                    }
                }
                if (position == 2) {
                    binding.introNextBtn.apply {
                        text = "Get Started"
                    }
                } else {
                    binding.introNextBtn.apply {
                        text = "Next"
                    }
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })

        binding.introNextBtn.setOnClickListener {
            val current = binding.viewPagerIntro.currentItem
            if (current == 2) openMain()
            else binding.viewPagerIntro.currentItem = current + 1
        }
        binding.btnIntroBack.setOnClickListener {
            val current = binding.viewPagerIntro.currentItem
            binding.viewPagerIntro.currentItem = current - 1
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openMain() {
        findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToHomeFragment())
        MySharedPreferencesImpl.getInstance().setRegister(true)
    }

}