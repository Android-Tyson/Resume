package com.historymakers.resume.repository

import com.historymakers.resume.model.Profile
import com.historymakers.resume.model.WorkHistory
import kotlinx.coroutines.flow.Flow

interface WorkHistoryListRepository {
    suspend fun getWorkHistoryFromDataSource() : Flow<Result<List<WorkHistory>>>
}