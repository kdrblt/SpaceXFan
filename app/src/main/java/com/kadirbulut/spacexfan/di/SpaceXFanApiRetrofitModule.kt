package com.kadirbulut.spacexfan.di

import com.kadirbulut.spacexfan.common.util.Constants.SPACEX_BASE_URL
import com.kadirbulut.spacexfan.data.source.remote.SpaceXServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn
@Module
class SpaceXFanApiRetrofitModule {
    @Provides
    @Singleton
    fun provideSpaceXService(): SpaceXServices = Retrofit.Builder()
        .baseUrl(SPACEX_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SpaceXServices::class.java)
}
