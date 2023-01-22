package com.krea.kollege.core.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {

    const val SHARED_PREFS_NAME = "MY_TEST_FOR_WORLD_SKILLS_APP"

    @Provides
    @Singleton
    fun provideSharedPrefs(
        @ApplicationContext context: Context
    ) : SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }
}