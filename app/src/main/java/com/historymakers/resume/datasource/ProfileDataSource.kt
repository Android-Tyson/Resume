package com.historymakers.resume.datasource

import com.historymakers.resume.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    suspend fun getProfileFromFirebaseService(): Flow<Result<Profile>>
}