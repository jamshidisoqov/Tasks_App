package uz.gita.task_app.data

import android.app.Application
import uz.gita.task_app.data.room.AppDatabase

// Created by Jamshid Isoqov an 8/10/2022
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }

}