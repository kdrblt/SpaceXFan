package com.kadirbulut.spacexfan.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.kadirbulut.spacexfan.domain.repository.FirebaseAuthRepository
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthRepository {
    override suspend fun login(email: String, password: String): AuthResult {
        return firebaseAuth.signInWithEmailAndPassword(
            email,
            password
        ).await()
    }

    override suspend fun logOut() {
        firebaseAuth.signOut()
    }
}
