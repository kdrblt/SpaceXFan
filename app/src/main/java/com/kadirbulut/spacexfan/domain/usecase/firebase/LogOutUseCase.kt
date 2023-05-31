package com.kadirbulut.spacexfan.domain.usecase.firebase

import com.kadirbulut.spacexfan.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository
) {
    suspend operator fun invoke() {
        firebaseAuthRepository.logOut()
    }
}
