package com.example.notebookexample2.dagger

import com.example.notebookexample2.repo.AuthDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AuthModule {
    @Provides
    @Singleton
    fun provideAuthDaoRepository() : AuthDaoRepository {
        return AuthDaoRepository()
    }


}