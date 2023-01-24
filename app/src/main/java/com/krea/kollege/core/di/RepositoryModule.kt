package com.krea.kollege.core.di

import android.content.SharedPreferences
import com.krea.kollege.data.repository.RoomRepositoryImpl
import com.krea.kollege.domain.repository.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRoomRepository(sharedPreferences: SharedPreferences): RoomRepository {
        return RoomRepositoryImpl(
            sharedPreferences
        )
    }

}