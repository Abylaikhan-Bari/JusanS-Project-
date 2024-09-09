package com.aikei.jusan.di

import com.aikei.jusan.data.api.CommentApiService
import com.aikei.jusan.data.api.PostApiService
import com.aikei.jusan.domain.repository.AlbumRepository
import com.aikei.jusan.domain.repository.CommentRepository
import com.aikei.jusan.domain.repository.CommentRepositoryImpl
import com.aikei.jusan.domain.repository.PostRepository
import com.aikei.jusan.domain.repository.PostRepositoryImpl
import com.aikei.jusan.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCommentApiService(): CommentApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com") // Use the correct base URL
            .build()
            .create(CommentApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(): AlbumRepository {
        return AlbumRepository() // Replace with actual implementation
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepository() // Replace with actual implementation
    }

    @Provides
    @Singleton
    fun provideCommentRepository(
        apiService: CommentApiService
    ): CommentRepository {
        return CommentRepositoryImpl(apiService) // Pass the apiService here
    }
}
