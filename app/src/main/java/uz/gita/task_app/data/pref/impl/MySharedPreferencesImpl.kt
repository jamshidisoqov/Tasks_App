package uz.gita.task_app.data.pref.impl

import android.content.Context
import android.content.SharedPreferences
import uz.gita.task_app.data.pref.MySharedPreferences

class MySharedPreferencesImpl private constructor(ctx: Context) : MySharedPreferences {

    private val sharedPreferences: SharedPreferences =
        ctx.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun getRegister(): Boolean = sharedPreferences.getBoolean(REGISTER, false)

    override fun setRegister(register: Boolean) {
        editor.putBoolean(REGISTER, true)
        editor.apply()
    }

    override fun getName(): String = sharedPreferences.getString(NAME, "Youre Name").toString()

    override fun setName(name: String) {
        editor.putString(NAME, name)
        editor.apply()
    }

    companion object {

        const val SHARED_NAME = "app_data"
        const val REGISTER = "is_register"
        const val NAME = "name"

        private lateinit var instance: MySharedPreferences

        fun init(ctx: Context) {
            instance = MySharedPreferencesImpl(ctx)
        }

        fun getInstance() = instance


    }
}