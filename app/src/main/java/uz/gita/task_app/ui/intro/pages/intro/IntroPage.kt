package uz.gita.task_app.ui.intro.pages.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.gita.task_app.R
import uz.gita.task_app.databinding.PageIntroBinding

// Created by Jamshid Isoqov an 8/12/2022
class IntroPage : Fragment(R.layout.page_intro) {

    private lateinit var binding: PageIntroBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = PageIntroBinding.bind(view)

        val args = arguments?.getInt("pos")!!

        binding.apply {
            tvIntroTitle.text = resources.getStringArray(R.array.intro_titles)[args]
            tvIntroDescription.text = resources.getStringArray(R.array.intro_subtitles)[args]
            headerImageIntro.setImageResource(
                when (args) {
                    0 -> R.drawable.ic_intro1
                    1 -> R.drawable.ic_intro2
                    else -> R.drawable.ic_intro3
                }
            )
        }

    }

}