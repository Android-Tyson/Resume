package com.historymakers.resume.di

import com.historymakers.resume.datasource.ProfileDataSource
import com.historymakers.resume.datasource.ProfileDataSourceImpl
import com.historymakers.resume.repository.ProfileRepository
import com.historymakers.resume.repository.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class ProfileModule {
    @Binds
    abstract fun bindProfileRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun bindProfileDataSource(dataSourceImpl: ProfileDataSourceImpl): ProfileDataSource
}

