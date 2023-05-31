package com.kadirbulut.spacexfan.domain.repository

import com.google.firebase.auth.AuthResult

interface FirebaseAuthRepository {
    suspend fun login(email: String, password: String): AuthResult
    suspend fun logOut()
}
