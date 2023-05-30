package com.kadirbulut.spacexfan.di

import com.kadirbulut.spacexfan.data.source.remote.SpaceXRemoteDataSourceImpl
import com.kadirbulut.spacexfan.data.source.remote.SpaceXServices
import com.kadirbulut.spacexfan.domain.datasource.remote.SpaceXRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideSpaceXRemoteDataSource(
        spaceXServices: SpaceXServices
    ): SpaceXRemoteDataSource = SpaceXRemoteDataSourceImpl(spaceXServices)
}
