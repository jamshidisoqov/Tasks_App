package uz.gita.task_app.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import uz.gita.task_app.MainActivity
import uz.gita.task_app.data.models.CategoryEntity

// Created by Jamshid Isoqov an 8/10/2022
object Constants {
    val categoryList = listOf(
        CategoryEntity(1,"Grocery","ic_bread"),
        CategoryEntity(2,"Work","ic_briefcase"),
        CategoryEntity(3,"Sport","ic_sport"),
        CategoryEntity(4,"Design","ic_design"),
        CategoryEntity(5,"University","ic_university"),
        CategoryEntity(6,"Social","ic_megaphone"),
        CategoryEntity(7,"Music","ic_music"),
        CategoryEntity(8,"Health","ic_heartbeat"),
        CategoryEntity(9,"Movie","ic_video"),
        CategoryEntity(10,"Home","ic_home"),
    )

    fun goToPlayMarket(activity: MainActivity){
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=uz.gita.task_app")
                )
            )
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=uz.gita.task_app")
                )
            )
        }
    }
}