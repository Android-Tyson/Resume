package com.historymakers.resume.ui.workhistory.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.historymakers.resume.model.WorkHistory
import com.historymakers.resume.repository.WorkHistoryListRepository
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class WorkHistoryListViewModel @Inject constructor(
    private val repositoryImpl: WorkHistoryListRepository
) : ViewModel() {

    var loader = MutableLiveData<Boolean>()

    val workHistoryListLiveData = liveData<Result<List<WorkHistory>>> {
        loader.postValue(true)
        emitSource(repositoryImpl.getWorkHistoryFromDataSource()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())
    }
}