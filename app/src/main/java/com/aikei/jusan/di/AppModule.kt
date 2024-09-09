package com.aikei.jusan.di

import com.aikei.jusan.data.api.PostApiService
import com.aikei.jusan.domain.repository.AlbumRepository
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

//    @Provides
//    @Singleton
//    fun providePostApiService(): PostApiService {
//        // Provide PostApiService
//        return Retrofit.Builder()
//            .baseUrl("https://api.example.com")
//            .build()
//            .create(PostApiService::class.java)
//    }

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
}
