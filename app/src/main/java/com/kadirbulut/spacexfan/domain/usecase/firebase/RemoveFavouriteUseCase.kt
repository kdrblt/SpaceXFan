package com.kadirbulut.spacexfan.domain.usecase.firebase

import com.google.android.gms.tasks.Task
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.domain.repository.FirebaseFireStoreRepository
import java.lang.Exception
import javax.inject.Inject

class RemoveFavouriteUseCase @Inject constructor(
    private val firebaseFireStoreRepository: FirebaseFireStoreRepository
) {
    data class Params(
        val userEmail: String,
        val rocketId: String
    )

    suspend operator fun invoke(parameters: Params): CallBack<Task<Void>> {
        return try {
            CallBack.OnLoading
            CallBack.OnSuccess(
                firebaseFireStoreRepository.removeFavouriteRocket(
                    parameters.userEmail,
                    parameters.rocketId
                )
            )
        } catch (e: Exception) {
            CallBack.OnError(e)
        }
    }
}
