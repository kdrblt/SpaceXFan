package com.kadirbulut.spacexfan.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FireBaseAuthModule {
    @Provides
    @Singleton
    fun providesFirebaseAuth() = Firebase.auth
}
