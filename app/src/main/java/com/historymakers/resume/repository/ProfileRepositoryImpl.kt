package com.historymakers.resume.repository

import com.historymakers.resume.datasource.ProfileDataSource
import com.historymakers.resume.model.Profile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getProfileFromDataSource(): Flow<Result<Profile>> {
        return dataSource.getProfileFromFirebaseService()
    }
}