package uz.gita.task_app.domain.presenter.main.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.task_app.data.pref.impl.MySharedPreferencesImpl
import uz.gita.task_app.domain.presenter.main.ProfileViewModel

class ProfileViewModelImpl : ViewModel(), ProfileViewModel {

    private var sharedPreferences = MySharedPreferencesImpl.getInstance()

    override val nameLiveData: MutableLiveData<String> =
        MutableLiveData(sharedPreferences.getName())

    override val imageLiveData: MutableLiveData<String> = MutableLiveData(sharedPreferences.getImageUri())

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val changeNameLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val changeImageLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val aboutUsLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val contactLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val supportLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun changeName() {
        changeNameLiveData.postValue(Unit)
    }

    override fun changeImage() {
        changeImageLiveData.postValue(Unit)
    }

    override fun aboutClicked() {
        aboutUsLiveData.postValue(Unit)
    }

    override fun helpClicked() {
        contactLiveData.postValue(Unit)
    }

    override fun supportClicked() {
        supportLiveData.postValue(Unit)
    }

    override fun setName(name: String) {
        sharedPreferences.setName(name)
        nameLiveData.postValue(name)
    }

    override fun setImage() {

    }

    override fun setImage(str: String) {
        sharedPreferences.setImageUri(str)
        imageLiveData.postValue(str)
    }

    override fun backClick() {
        backLiveData.postValue(Unit)
    }
}