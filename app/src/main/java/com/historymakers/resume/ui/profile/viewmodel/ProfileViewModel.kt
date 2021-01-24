package com.historymakers.resume.ui.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.historymakers.resume.model.Profile
import com.historymakers.resume.repository.ProfileRepository
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    var loader = MutableLiveData<Boolean>()

    val profileLiveData = liveData<Result<Profile>> {
        loader.postValue(true)
        emitSource(repository.getProfileFromDataSource()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())
    }
}