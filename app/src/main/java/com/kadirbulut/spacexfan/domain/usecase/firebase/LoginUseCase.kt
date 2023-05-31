package com.kadirbulut.spacexfan.domain.usecase.firebase

import com.google.firebase.auth.FirebaseUser
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.domain.repository.FirebaseAuthRepository
import java.lang.Exception
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository
) {
    data class Params(
        val email: String,
        val password: String
    )

    suspend operator fun invoke(parameters: Params): CallBack<FirebaseUser> {
        return try {
            CallBack.OnLoading
            CallBack.OnSuccess(
                firebaseAuthRepository.login(
                    parameters.email,
                    parameters.password
                ).user!!
            )
        } catch (e: Exception) {
            CallBack.OnError(e)
        }
    }
}
