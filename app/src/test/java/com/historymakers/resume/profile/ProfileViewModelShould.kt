package com.historymakers.resume.profile

import com.historymakers.resume.datasource.ProfileDataSource
import com.historymakers.resume.datasource.ProfileDataSourceImpl
import com.historymakers.resume.model.Profile
import com.historymakers.resume.repository.ProfileRepository
import com.historymakers.resume.repository.ProfileRepositoryImpl
import com.historymakers.resume.ui.profile.viewmodel.ProfileViewModel
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
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.RuntimeException

class ProfileViewModelShould : BaseUnitTest() {
    lateinit var viewModel: ProfileViewModel
    private var repository: ProfileRepository = mock()
    private val profile: Profile = mock()
    private val expectedResult = Result.success(profile)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getProfileFromRepository() = runBlockingTest {
        viewModel = mockSuccessfulCase()

        viewModel.profileLiveData.getValueForTest()

        verify(repository, times(1)).getProfileFromDataSource()
    }

    @Test
    fun emitProfileFromRepository() = runBlockingTest {
        viewModel = mockSuccessfulCase()

        assertEquals(expectedResult, viewModel.profileLiveData.getValueForTest())
    }

    @Test
    fun showProgressBarWhileLoading() = runBlockingTest {
        viewModel = mockSuccessfulCase()
        viewModel.loader.captureValues {
            viewModel.profileLiveData.getValueForTest()
            viewModel.loader.getValueForTest()
            assertEquals(true, values[0])
        }
    }

    @Test
    fun hideProgressBarAfterLoading() = runBlockingTest {
        viewModel = mockSuccessfulCase()
        viewModel.loader.captureValues {
            viewModel.profileLiveData.getValueForTest()
            viewModel.loader.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun hideProgressBarWhileError() = runBlockingTest {
        val viewModel = mockFailingCase()
        viewModel.loader.captureValues {
            viewModel.profileLiveData.getValueForTest()
            viewModel.loader.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    private fun mockSuccessfulCase(): ProfileViewModel {
        runBlocking {
            whenever(repository.getProfileFromDataSource()).thenReturn(flow {
                emit(expectedResult)
            })
        }
        return ProfileViewModel(repository)

    }

    private fun mockFailingCase(): ProfileViewModel {
        runBlocking {
            whenever(repository.getProfileFromDataSource()).thenReturn(
                flow {
                    emit(Result.failure<Profile>(exception))
                }
            )
        }
        return ProfileViewModel(repository)
    }
}