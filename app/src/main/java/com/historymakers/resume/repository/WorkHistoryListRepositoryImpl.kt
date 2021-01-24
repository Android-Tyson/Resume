package com.historymakers.resume.repository

import com.historymakers.resume.datasource.WorkHistoryListDataSource
import com.historymakers.resume.model.WorkHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkHistoryListRepositoryImpl @Inject constructor(
    private val dataSource: WorkHistoryListDataSource
) : WorkHistoryListRepository {
    override suspend fun getWorkHistoryFromDataSource(): Flow<Result<List<WorkHistory>>> {
        return dataSource.getWorkHistoryListFromFirebase()
    }
}