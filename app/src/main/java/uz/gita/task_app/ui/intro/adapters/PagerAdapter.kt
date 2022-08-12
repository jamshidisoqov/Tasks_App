package uz.gita.task_app.ui.intro.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.task_app.ui.intro.pages.intro.IntroPage

// Created by Jamshid Isoqov an 8/2/2022
class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val introFragment = IntroPage()
        introFragment.arguments = Bundle().apply {
            putInt("pos", position)
        }
        return introFragment
    }
}