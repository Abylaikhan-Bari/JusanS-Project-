package com.aikei.jusan.di

import com.aikei.jusan.domain.repository.AlbumRepository
import com.aikei.jusan.domain.repository.PostRepository
import com.aikei.jusan.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePostRepository(): PostRepository {
        return PostRepository() // Replace with actual implementation
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(): AlbumRepository {
        return AlbumRepository() // Replace with actual implementation
    }

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }
}
