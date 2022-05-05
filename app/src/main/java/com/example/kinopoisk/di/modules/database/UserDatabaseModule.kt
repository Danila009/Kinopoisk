package com.example.kinopoisk.di.modules.database

import android.content.Context
import com.example.core_database_data.repository.UserDatabaseRepositoryImpl
import com.example.core_database_domain.repository.UserDatabaseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDatabaseModule {

    @[Singleton Provides]
    fun providerUserDatabaseRepository(
        context:Context
    ):UserDatabaseRepository = UserDatabaseRepositoryImpl(
        context = context
    )
}