package com.kadirbulut.spacexfan.di

import com.kadirbulut.spacexfan.data.repository.SpaceXRepositoryImpl
import com.kadirbulut.spacexfan.domain.datasource.remote.SpaceXRemoteDataSource
import com.kadirbulut.spacexfan.domain.repository.SpaceXRepository
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
    fun provideSpaceXRepository(
        spaceXRemoteDataSource: SpaceXRemoteDataSource
    ): SpaceXRepository {
        return SpaceXRepositoryImpl(spaceXRemoteDataSource)
    }
}
