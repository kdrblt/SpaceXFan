package com.kadirbulut.spacexfan.di

import com.google.firebase.auth.FirebaseAuth
import com.kadirbulut.spacexfan.data.repository.FirebaseAuthRepositoryImpl
import com.kadirbulut.spacexfan.domain.repository.FirebaseAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseRepositoryModule {
    @Provides
    @Singleton
    fun providesFirebaseRepository(firebaseAuth: FirebaseAuth): FirebaseAuthRepository =
        FirebaseAuthRepositoryImpl(firebaseAuth)
}
