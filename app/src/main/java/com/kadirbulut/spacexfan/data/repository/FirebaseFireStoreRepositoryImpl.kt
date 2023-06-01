package com.kadirbulut.spacexfan.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.kadirbulut.spacexfan.common.util.Constants
import com.kadirbulut.spacexfan.domain.repository.FirebaseFireStoreRepository
import javax.inject.Inject

class FirebaseFireStoreRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirebaseFireStoreRepository {

    override suspend fun getFavouriteRockets(userEmail: String) =
        firestore.collection(Constants.DB_FAVOURITES_TABLE_NAME)
            .document(userEmail)
            .get()
            .addOnSuccessListener {
                return@addOnSuccessListener
            }
            .addOnFailureListener {
                return@addOnFailureListener
            }
            /*.result.data?.values?.toString()
            ?.trim()
            ?.replace("[","")
            ?.replace("]","")
            ?.replace(" ","")
            ?.split(",")*/

    override suspend fun addFavouriteRocket(userEmail: String, rocketId: String) =
        firestore
            .collection(Constants.DB_FAVOURITES_TABLE_NAME)
            .document(userEmail)
            .update(Constants.DB_ROCKETS_FIELD_NAME, FieldValue.arrayUnion(rocketId))

    override suspend fun removeFavouriteRocket(userEmail: String, rocketId: String) =
        firestore
            .collection(Constants.DB_FAVOURITES_TABLE_NAME)
            .document(userEmail)
            .update(Constants.DB_ROCKETS_FIELD_NAME, FieldValue.arrayRemove(rocketId))
}
