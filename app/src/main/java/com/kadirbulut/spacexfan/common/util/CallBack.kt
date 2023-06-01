package com.kadirbulut.spacexfan.common.util

/*
 * Sealed class for service response results
 */
sealed class CallBack<out T : Any> {
    data class OnSuccess<out T : Any>(val data: T) : CallBack<T>()
    data class OnError<out T : Any>(val error: Throwable) : CallBack<T>()
    object OnLoading : CallBack<Nothing>()
}

data class Error(
    val status: String,
    val code: String,
    val message: String
)
