package com.historymakers.resume.workhistory

import com.historymakers.resume.datasource.WorkHistoryListDataSource
import com.historymakers.resume.model.WorkHistory
import com.historymakers.resume.repository.WorkHistoryListRepository
import com.historymakers.resume.repository.WorkHistoryListRepositoryImpl
import com.historymakers.resume.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.RuntimeException

class WorkHistoryListRepositoryShould : BaseUnitTest() {
    lateinit var repository: WorkHistoryListRepository
    private val dataSource: WorkHistoryListDataSource = mock()
    private val exception = RuntimeException("Something went wrong")
    private val workHistoryList = mock<List<WorkHistory>>()


    @Test
    fun getWorkHistoryFromDataSource() = runBlockingTest {
        repository = mockSuccessfulCase()
        repository.getWorkHistoryFromDataSource()

        verify(dataSource, times(1)).getWorkHistoryListFromFirebase()
    }

    @Test
    fun emitWorkHistoryListFromDataSource() = runBlockingTest {
        repository = mockSuccessfulCase()

        assertEquals(workHistoryList, repository.getWorkHistoryFromDataSource().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runBlockingTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getWorkHistoryFromDataSource().first().exceptionOrNull())
    }

    private suspend fun mockFailureCase(): WorkHistoryListRepository {
        whenever(dataSource.getWorkHistoryListFromFirebase()).thenReturn(
            flow {
                emit(Result.failure<List<WorkHistory>>(exception))
            })
        return WorkHistoryListRepositoryImpl(dataSource)
    }


    private suspend fun mockSuccessfulCase(): WorkHistoryListRepository {
        whenever(dataSource.getWorkHistoryListFromFirebase()).thenReturn(
            flow {
                emit(Result.success(workHistoryList))
            })
        return WorkHistoryListRepositoryImpl(dataSource)
    }
}