package com.historymakers.resume.datasource

import com.historymakers.resume.model.WorkHistory
import com.historymakers.resume.remote.WorkHistoryListApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WorkHistoryListDataSourceImpl @Inject constructor(
    private val apiService: WorkHistoryListApiService
): WorkHistoryListDataSource {
    override suspend fun getWorkHistoryListFromFirebase(): Flow<Result<List<WorkHistory>>> {
        return flow {
            emit(Result.success(apiService.fetchProfile()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}