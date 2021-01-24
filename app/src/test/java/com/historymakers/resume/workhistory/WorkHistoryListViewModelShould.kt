package com.historymakers.resume.workhistory

import com.historymakers.resume.model.WorkHistory
import com.historymakers.resume.repository.WorkHistoryListRepository
import com.historymakers.resume.ui.workhistory.viewmodel.WorkHistoryListViewModel
import com.historymakers.resume.utils.BaseUnitTest
import com.historymakers.resume.utils.captureValues
import com.historymakers.resume.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import java.lang.RuntimeException

class WorkHistoryListViewModelShould : BaseUnitTest() {
    lateinit var viewModel: WorkHistoryListViewModel
    private val repository: WorkHistoryListRepository = mock()
    private val workHistory = mock<List<WorkHistory>>()
    private val expected = Result.success(workHistory)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getWorkHistoryListFromRepository() = runBlockingTest {
        viewModel = mockSuccessfulCase()
        viewModel.workHistoryListLiveData.getValueForTest()

        verify(repository, times(1)).getWorkHistoryFromDataSource()
    }

    @Test
    fun emitWorkHistoryListFromRepository() = runBlockingTest {
        viewModel = mockSuccessfulCase()

        Assert.assertEquals(expected, viewModel.workHistoryListLiveData.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceivedError() {
        val viewModel = mockFailingCase()
        Assert.assertEquals(
            exception,
            viewModel.workHistoryListLiveData.getValueForTest()!!.exceptionOrNull()
        )
    }

    @Test
    fun showProgressBarWhileLoading() = runBlockingTest {
        mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.workHistoryListLiveData.getValueForTest()

            viewModel.loader.getValueForTest()
            Assert.assertEquals(true, values[0])
        }
    }

    @Test
    fun hideProgressBarAfterLoading() = runBlockingTest {
        mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.workHistoryListLiveData.getValueForTest()

            viewModel.loader.getValueForTest()
            Assert.assertEquals(false, values.last())
        }
    }

    private fun mockSuccessfulCase(): WorkHistoryListViewModel {
        runBlocking {
            whenever(repository.getWorkHistoryFromDataSource()).thenReturn(flow {
                emit(expected)
            })
        }
        return WorkHistoryListViewModel(repository)

    }

    private fun mockFailingCase(): WorkHistoryListViewModel {
        runBlocking {
            whenever(repository.getWorkHistoryFromDataSource()).thenReturn(
                flow {
                    emit(Result.failure<List<WorkHistory>>(exception))
                }
            )
        }
        return WorkHistoryListViewModel(repository)
    }
}