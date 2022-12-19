package ru.philipp_kalyaev.android.education_api_git.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {
    @Provides
    fun context(): Context = context
}