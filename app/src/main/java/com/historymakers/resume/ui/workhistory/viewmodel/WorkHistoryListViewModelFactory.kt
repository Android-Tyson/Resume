package com.historymakers.resume.ui.workhistory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.historymakers.resume.repository.WorkHistoryListRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class WorkHistoryListViewModelFactory @Inject constructor(
    private val repository : WorkHistoryListRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WorkHistoryListViewModel(repository) as T
    }
}