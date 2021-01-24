package com.historymakers.resume.repository

import com.historymakers.resume.model.Profile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProfileRepository{
    suspend fun getProfileFromDataSource() : Flow<Result<Profile>>
}