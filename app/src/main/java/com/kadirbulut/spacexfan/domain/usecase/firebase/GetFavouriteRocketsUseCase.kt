package com.kadirbulut.spacexfan.domain.usecase.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.domain.repository.FirebaseFireStoreRepository
import java.lang.Exception
import javax.inject.Inject

class GetFavouriteRocketsUseCase @Inject constructor(
    private val firebaseFireStoreRepository: FirebaseFireStoreRepository
) {
    data class Params(
        val userEmail: String
    )

    suspend operator fun invoke(parameters: Params): CallBack<Task<DocumentSnapshot>> {
        return try {
            CallBack.OnLoading
            CallBack.OnSuccess(
                firebaseFireStoreRepository.getFavouriteRockets(parameters.userEmail)
            )
        } catch (e: Exception) {
            CallBack.OnError(e)
        }
    }
}
