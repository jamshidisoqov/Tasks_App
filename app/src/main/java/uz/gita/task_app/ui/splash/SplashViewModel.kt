package uz.gita.task_app.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.task_app.data.pref.MySharedPreferences
import uz.gita.task_app.data.pref.impl.MySharedPreferencesImpl

class SplashViewModel : ViewModel() {

    private val sharedPreferences: MySharedPreferences = MySharedPreferencesImpl.getInstance()

    private var _openMainLiveData: MutableLiveData<Unit> = MutableLiveData()
    val openMainLiveData: LiveData<Unit> = _openMainLiveData

    private var _openIntroLiveData: MutableLiveData<Unit> = MutableLiveData()
    val openIntroLiveData: LiveData<Unit> = _openIntroLiveData

    init {
        viewModelScope.launch {
            delay(2000)
            if (sharedPreferences.getRegister())
                _openMainLiveData.postValue(Unit)
            else _openIntroLiveData.postValue(Unit)
        }
    }
}