package com.kadirbulut.spacexfan.di

import android.content.Context
import android.content.SharedPreferences
import com.kadirbulut.spacexfan.common.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SPACEX_SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }
}
