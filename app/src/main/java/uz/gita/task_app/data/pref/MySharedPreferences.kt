package uz.gita.task_app.data.pref

// Created by Jamshid Isoqov an 8/12/2022
interface MySharedPreferences {

    fun getRegister(): Boolean

    fun setRegister(register: Boolean)

    fun getName(): String

    fun setName(name: String)

}