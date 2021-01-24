package com.historymakers.resume.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.historymakers.resume.R
import com.historymakers.resume.datasource.ProfileDataSource
import com.historymakers.resume.remote.ProfileApiService
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideFirebaseInstance() = FirebaseFirestore.getInstance()

    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context)
        .applyDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
}