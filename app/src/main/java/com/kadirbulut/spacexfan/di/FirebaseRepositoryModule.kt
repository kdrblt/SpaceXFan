package com.kadirbulut.spacexfan.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kadirbulut.spacexfan.data.repository.FirebaseAuthRepositoryImpl
import com.kadirbulut.spacexfan.data.repository.FirebaseFireStoreRepositoryImpl
import com.kadirbulut.spacexfan.domain.repository.FirebaseAuthRepository
import com.kadirbulut.spacexfan.domain.repository.FirebaseFireStoreRepository
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
    fun providesFirebaseAuthRepository(firebaseAuth: FirebaseAuth):
        FirebaseAuthRepository = FirebaseAuthRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun providesFirebaseFireStoreRepository(firestore: FirebaseFirestore):
        FirebaseFireStoreRepository = FirebaseFireStoreRepositoryImpl(firestore)
}
