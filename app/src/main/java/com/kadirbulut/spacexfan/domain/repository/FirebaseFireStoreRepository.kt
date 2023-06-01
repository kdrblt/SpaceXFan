package com.kadirbulut.spacexfan.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface FirebaseFireStoreRepository {
    suspend fun getFavouriteRockets(userEmail: String): Task<DocumentSnapshot>
    suspend fun addFavouriteRocket(userEmail: String, rocketId: String): Task<Void>
    suspend fun removeFavouriteRocket(userEmail: String, rocketId: String): Task<Void>
}
