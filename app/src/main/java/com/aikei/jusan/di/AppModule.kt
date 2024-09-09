package com.aikei.jusan.di

import com.aikei.jusan.data.api.AlbumApiService
import com.aikei.jusan.data.api.CommentApiService
import com.aikei.jusan.data.api.PhotoApiService
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
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com") // Correct base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun providePhotoApiService(retrofit: Retrofit): PhotoApiService {
        return retrofit.create(PhotoApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideCommentApiService(retrofit: Retrofit): CommentApiService {
        return retrofit.create(CommentApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePostApiService(retrofit: Retrofit): PostApiService {
        return retrofit.create(PostApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideAlbumApiService(retrofit: Retrofit): AlbumApiService {
        return retrofit.create(AlbumApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideAlbumRepository(apiService: AlbumApiService): AlbumRepository {
        return AlbumRepository(apiService)
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
        return CommentRepositoryImpl(apiService)
    }


}