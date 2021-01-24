package com.historymakers.resume.profile

import com.historymakers.resume.datasource.ProfileDataSource
import com.historymakers.resume.datasource.ProfileDataSourceImpl
import com.historymakers.resume.model.Profile
import com.historymakers.resume.repository.ProfileRepository
import com.historymakers.resume.repository.ProfileRepositoryImpl
import com.historymakers.resume.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileRepositoryShould : BaseUnitTest() {
    lateinit var repository: ProfileRepository
    private val dataSource: ProfileDataSource = mock()
    private val profile: Profile = mock()
    private val exception = RuntimeException("Damn backend again")

    @Test
    fun fetchProfileFromDataSource() = runBlockingTest {
        repository = ProfileRepositoryImpl(dataSource)
        repository.getProfileFromDataSource()

        verify(dataSource, times(1)).getProfileFromFirebaseService()
    }

    @Test
    fun emitFlowProfileFromService() = runBlockingTest {
        repository = mockSuccessfulCase()

        assertEquals(profile, dataSource.getProfileFromFirebaseService().first().getOrNull())
    }


    @Test
    fun propagateErrors() = runBlockingTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getProfileFromDataSource().first().exceptionOrNull())
    }


    private suspend fun mockSuccessfulCase(): ProfileRepositoryImpl {
        whenever(dataSource.getProfileFromFirebaseService()).thenReturn(
            flow {
                emit(Result.success(profile))
            })
        return ProfileRepositoryImpl(dataSource)
    }
    private suspend fun mockFailureCase(): ProfileRepository {
        whenever(dataSource.getProfileFromFirebaseService()).thenReturn(
            flow {
                emit(Result.failure<Profile>(exception))
            })
        return ProfileRepositoryImpl(dataSource)
    }

}