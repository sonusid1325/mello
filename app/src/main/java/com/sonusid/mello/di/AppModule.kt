package com.sonusid.mello.di

import com.sonusid.mello.data.posts.PostRepository
import com.sonusid.mello.data.posts.PostRepositoryImpl
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
    fun providePostRepository(): PostRepository = PostRepositoryImpl()
}
