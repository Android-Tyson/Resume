package com.historymakers.resume.datasource

import com.historymakers.resume.model.Profile
import com.historymakers.resume.remote.ProfileApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val profileApiService: ProfileApiService
) : ProfileDataSource {
    override suspend fun getProfileFromFirebaseService(): Flow<Result<Profile>> {
        return flow {
            emit(Result.success(profileApiService.fetchProfile()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}