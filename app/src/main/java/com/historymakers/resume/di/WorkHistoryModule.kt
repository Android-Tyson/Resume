package com.historymakers.resume.di

import com.historymakers.resume.datasource.ProfileDataSource
import com.historymakers.resume.datasource.ProfileDataSourceImpl
import com.historymakers.resume.datasource.WorkHistoryListDataSource
import com.historymakers.resume.datasource.WorkHistoryListDataSourceImpl
import com.historymakers.resume.repository.ProfileRepository
import com.historymakers.resume.repository.ProfileRepositoryImpl
import com.historymakers.resume.repository.WorkHistoryListRepository
import com.historymakers.resume.repository.WorkHistoryListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class WorkHistoryModule {
    @Binds
    abstract fun bindWorkHistoryRepository(repository: WorkHistoryListRepositoryImpl): WorkHistoryListRepository

    @Binds
    abstract fun bindWorkHistoryDataSource(dataSourceImpl: WorkHistoryListDataSourceImpl): WorkHistoryListDataSource
}