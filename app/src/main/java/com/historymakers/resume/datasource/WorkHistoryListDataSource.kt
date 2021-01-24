package com.historymakers.resume.datasource

import com.historymakers.resume.model.WorkHistory
import kotlinx.coroutines.flow.Flow

interface WorkHistoryListDataSource {
    suspend fun getWorkHistoryListFromFirebase() : Flow<Result<List<WorkHistory>>>
}