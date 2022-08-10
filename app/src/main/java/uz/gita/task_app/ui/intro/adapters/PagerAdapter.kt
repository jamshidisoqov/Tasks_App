package uz.gita.task_app.ui.intro.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.task_app.ui.intro.pages.intro.IntroFragment

// Created by Jamshid Isoqov an 8/2/2022
class PagerAdapter(fa:FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int  = 5

    override fun createFragment(position: Int): Fragment {
        return IntroFragment()
    }
}